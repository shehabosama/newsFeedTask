package com.example.newsfeedtask.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.drawableCompat(@DrawableRes drawableResId: Int) =
    ContextCompat.getDrawable(this, drawableResId)

fun Context.openWebsite(webUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(webUrl)
    this.startActivity(intent)
}

fun Context.callSupport( phoneNumber: String) {
    val call = Uri.parse("tel:${phoneNumber}")
    val intent = Intent(Intent.ACTION_DIAL, call)
    this.startActivity(intent)
}

fun Context.sendMail(emailAddress: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("mailto:$emailAddress")
    this.startActivity(Intent.createChooser(intent, "E-mail"))
}