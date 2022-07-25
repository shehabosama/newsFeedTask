package com.example.newsfeedtask.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Patterns
import androidx.core.content.ContextCompat



//fun Activity.findNavController(@IdRes viewId: Int): NavController =
//    Navigation.findNavController(this, viewId)

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = this.length >= 6

/**
 * Gets Drawable according the the given isoCode
 *
 * @param isoCode the ISOCode of the color.
 *
 * @return Drawable.
 */
fun Context.getDrawableByISOCode(isoCode: String): Drawable? {
    val uri = "@drawable/$isoCode"
    val imageResource: Int = resources.getIdentifier(uri, null, packageName)

    return ContextCompat.getDrawable(this, imageResource)
}

/**
 * Gets DrawableResId according the the given isoCode
 *
 * @param isoCode the ISOCode of the color.
 *
 * @return Int, DrawableResId.
 */
fun Context.getDrawableResId(isoCode: String): Int {
    val uri = "@drawable/$isoCode"

    return resources.getIdentifier(uri, null, packageName)
}

//fun Fragment.navigateTo(destinationId: Int) {
//    this.findNavController().navigate(destinationId)
//}

/**
 * Gets Color from the String value
 *
 * @return Int, Color.
 */
fun String?.getColor(): Int {
    return Color.parseColor(this ?: Constants.COLOR_CONTENT_PRIMARY)
}

fun String?.fromHtml(): Spanned? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}
