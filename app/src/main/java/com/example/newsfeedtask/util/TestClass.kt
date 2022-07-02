package com.example.newsfeedtask.util

import android.util.Log
import javax.inject.Inject

class TestClass @Inject constructor() {
    init {
        Log.d("TestClass", ": Hello from test class ")
    }
}