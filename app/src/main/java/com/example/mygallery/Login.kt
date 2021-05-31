package com.example.mygallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mygallery.databinding.GridFragmentBinding
import com.example.mygallery.databinding.LoginFragmentBinding

class Login : Fragment() {

    lateinit var binding : LoginFragmentBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginbuton.setOnClickListener(){
            if("fayaz" == binding.user.text.toString()){
               this.findNavController().navigate(LoginDirections.actionLoginToGrid())
            }
            else{
                Toast.makeText(context,"Wrong user name or password",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


}