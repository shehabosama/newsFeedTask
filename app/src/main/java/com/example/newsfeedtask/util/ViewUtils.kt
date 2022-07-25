package com.example.newsfeedtask.util

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newsfeedtask.R


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.setVisibility(visible: Boolean) {
    if (visible) this.show()
    else this.hide()
}

fun TextView.showProgress(progressBar: ProgressBar) {
    this.text = ""
    progressBar.show()
}

fun TextView.hideProgress(@StringRes action: Int, progressBar: ProgressBar) {
    this.setText(action)
    progressBar.hide()
}

fun ImageView.setPasswordVisible(editText: EditText) {
    if (editText.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
        //Show Password
        editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        this.setImageResource(R.drawable.ic_show_filled)
    } else {
        //Hide Password
        editText.transformationMethod = PasswordTransformationMethod.getInstance()
        this.setImageResource(R.drawable.ic_hide_filled)
    }
}

fun ImageView.enable() {
    this.isEnabled = true
    this.setColorFilter(resources.getColor(R.color.colorContentPrimary))
}

/**
 * Disables the given ImageView.
 *
 * @return String value.
 */
fun ImageView.disable() {
    this.isEnabled = false
    this.setColorFilter(resources.getColor(R.color.colorContentThirdly))
}

/**
 * Gets text of the EditText.
 *
 * @return String value.
 */
val EditText.textString: String get() = this.text.toString()

/**
 * Sets HTML data in String format to the WebView.
 *
 * @param htmlData the HTML data
 */
fun WebView.loadPage(htmlData: String) {
    this.loadDataWithBaseURL(
        null,
        htmlData,
        "text/html",
        "UTF-8",
        null
    )
}


/**
 * Adds underline to the given TextView
 */
fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

/**
 * Sets image to the given ImageView
 *
 * @param url the URL of the image
 * @param placeholder the resource id of the placeholder drawable
 */
fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int = 0) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

/**
 * Sets image to the given ImageView
 *
 * @param resId the resource id of the drawable
 * @param placeholder the resource id of the placeholder drawable
 */
fun ImageView.loadImage(@DrawableRes resId: Int?, @DrawableRes placeholder: Int = 0) {
    Glide.with(this)
        .load(resId)
        .placeholder(placeholder)
        .into(this)
}

/**
 * Sets image to the given ImageView
 *
 * @param url the URL of the image
 * @param progressBar the Progressbar that should be shown while loading image
 */
fun ImageView.loadImageWithLoader(
    url: String?,
    progressBar: ProgressBar,
    @DrawableRes placeholder: Int = 0
) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .listener(object :
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.hide()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.hide()
                return false
            }
        })
        .into(this)
}

/**
 * Sets circular image to the given ImageView
 *
 * @param resId the resource id of the drawable
 * @param placeholder the resource id of the placeholder drawable
 */
fun ImageView.loadRoundedImage(@DrawableRes resId: Int?, @DrawableRes placeholder: Int = 0) {
    Glide.with(this)
        .load(resId)
        .placeholder(placeholder)
        .circleCrop()
        .into(this)
}

/**
 * Check if layout direction is RTL
 *
 * @param context the current context
 * @return `true` if the layout direction is right-to-left
 */
fun isRtl(context: Context): Boolean {
    return context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
}

/**
 * Lightens a color by a given factor.
 *
 * @param color  The color to lighten
 * @param factor The factor to lighten the color. 0 will make the color unchanged. 1 will make the
 * color white.
 * @return lighter version of the specified color.
 */
fun lighter(color: Int, factor: Float): Int {
    val red = ((Color.red(color) * (1 - factor) / 255 + factor) * 255).toInt()
    val green = ((Color.green(color) * (1 - factor) / 255 + factor) * 255).toInt()
    val blue = ((Color.blue(color) * (1 - factor) / 255 + factor) * 255).toInt()
    return Color.argb(Color.alpha(color), red, green, blue)
}

