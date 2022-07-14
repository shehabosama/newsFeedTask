package com.example.newsfeedtask.ui.fragments.uploadImage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Observable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.newsfeedtask.R
import com.example.newsfeedtask.network.entities.ResponseStatus
import com.example.newsfeedtask.util.DataState
import com.example.newsfeedtask.util.Helper
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

const val REQUEST_WRITE_PERMISSION = 1
@AndroidEntryPoint
class UploadImageToServerFragment : Fragment() {
    private val viewModel: UploadImageViewModel by viewModels()

    @Inject
    lateinit var helper: Helper
    private var file:File? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()

    }
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            toGallery()
        }
    }
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(requireContext() , "permission granted" , Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext() , "permission is not granted " , Toast.LENGTH_LONG).show()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          //  toGallery();
        }
    }

    private fun subscribeObserver(){
        viewModel.dataUploadImageState.observe(viewLifecycleOwner, Observer {dataState->
            when(dataState){
                is DataState.Success<ResponseStatus> ->{
                    Log.d("success", "subscribeObserver: ${dataState.data}")
                }
                is DataState.Loading->{
                    Log.d("loading", "subscribeObserver: loading ")
                }
                is DataState.Error->{
                    Log.e("Error", "subscribeObserver: ${dataState.exception.localizedMessage}" )
                }
            }
        })
    }
    private val pickPhotoForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->

        if (result.resultCode == Activity.RESULT_OK){
            result.data?.data?.let { uri ->
                helper.getFileFromUri(requireContext(), uri)
                 file =  helper.getFileFromUri(requireContext(), uri)!!

            }
        }
    }


    private fun uploadImageAction(file: File?){
        try {
            viewModel.setStateEvent(MainStateEvent.UploadFileEvent, file =file)
        }catch (e:Exception){
            Log.e("Error uploading image", ":${e.localizedMessage} ")
        }
    }

    private fun toGallery(){
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "image/*"
        pickPhotoForResult.launch(pickIntent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_upload_image_to_server, container, false)
        val btn:Button = view.findViewById(R.id.btn)
        val btn2:Button = view.findViewById(R.id.btn1)
        subscribeObserver()
        btn.setOnClickListener(View.OnClickListener {
            toGallery()
        })
        btn2.setOnClickListener(View.OnClickListener {
            uploadImageAction(file)
        })
        return view
    }


}