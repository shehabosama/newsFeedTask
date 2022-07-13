package com.example.newsfeedtask.repository

import android.R.attr.value
import android.content.Context
import android.net.Uri
import android.os.FileUtils
import com.example.newsfeedtask.network.RetrofitAPI
import com.example.newsfeedtask.network.entities.ResponseStatus
import com.example.newsfeedtask.util.DataState
import com.example.newsfeedtask.util.Helper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject


class UploadImageRepository @Inject constructor(private val retrofit: RetrofitAPI){
    @Inject
    lateinit var helper:Helper
    lateinit var uri: Uri
    lateinit var context:Context
    private suspend fun uploadFile(inputFiles: List<String>,channel: Channel<Any>): Flow<DataState<ResponseStatus>> =
        flow{
            try {
//                val file = helper.getFileFromUri(uri, context)
//                val file: File = FileUtils.getFile(this, fileUri)
//                val requestBody: RequestBody =
//                    file.asRequestBody("image/jpeg".toMediaTypeOrNull())
//                val filename = value.toString() + file.getName()
//                val body = MultipartBody.Part.createFormData("uploaded_file", filename, requestFile)
                //retrofit.uploadFiles(partList)
            }catch (e:Exception){

            }
    }
}