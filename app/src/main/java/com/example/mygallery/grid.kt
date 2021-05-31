package com.example.mygallery

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.query
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mygallery.databinding.GridFragmentBinding
import com.example.mygallery.databinding.LoginFragmentBinding
import java.util.concurrent.TimeUnit


class grid : Fragment() {


    private lateinit var viewModel: GridViewModel
    lateinit var binding : GridFragmentBinding
    var imageList = mutableListOf<ImageModel>()
    val collection =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

    val projection = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE
    )
    val sortOrder = "${MediaStore.Images.Media.DISPLAY_NAME} ASC"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GridFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(GridViewModel::class.java)

        val adapter = GalleryAdapter(GalleryAdapter.OnClickListener{
            viewModel.displayImageDetails(it)
        })

        binding.galleryRv.adapter = adapter
        LoadImage()
        adapter.submitList(imageList)
        var listofImages = ListofImages(imageList)

        viewModel.navigateToImage.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(gridDirections.actionGridToDetail(it,listofImages))
                Toast.makeText(getContext(),"gyakfdga",Toast.LENGTH_SHORT).show()
                viewModel.displayImageDetailsComplete()
            }
        })

        return binding.root
    }
    fun LoadImage(){

      val query = context?.contentResolver?.query(
        collection,
          projection,
          null,null,
          sortOrder
      )
        query.use { cursor ->
            val idColumn = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn =
                cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // Get values of columns for a given video.
                    val id = idColumn?.let { cursor.getLong(it) }
                    val name = nameColumn?.let { cursor.getString(it) }

                    val contentUri: Uri? = id?.let {
                        ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            it
                        )
                    }

                    // Stores column values and the contentUri in a local object
                    // that represents the media file.
                    imageList.add(ImageModel(contentUri, name))
                }
            }

        }
    }

}