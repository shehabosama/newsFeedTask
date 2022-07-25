package com.example.newsfeedtask.widgets.toast

import android.annotation.SuppressLint
import android.app.Activity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.newsfeedtask.R

class ErrorToast
/**
 * Construct an empty Toast object.  You must call [.setView] before you
 * can call [.show].
 *
 * @param context The context to use.  Usually [Activity] object.
 */
private constructor(context: Activity) : Toast(context) {
    /**
     * Set toast view and its text.
     *
     * @param context The context to use.  Usually your [Activity] object.
     * @param resId   The resource id of the string resource to use.
     */
    private fun setMessage(context: Activity, @StringRes resId: Int) {
        @SuppressLint("InflateParams") val view: View =
            context.layoutInflater.inflate(R.layout.toast_error, null)
        val messageView = view.findViewById<TextView>(R.id.message)
        messageView.setText(resId)
        setView(view)
    }

    /**
     * Set toast view and its text.
     *
     * @param context The context to use.  Usually your [Activity] object.
     * @param message The text to show.
     */
    private fun setMessage(context: Activity, message: String) {
        @SuppressLint("InflateParams") val view: View =
            context.layoutInflater.inflate(R.layout.toast_error, null)
        val messageView = view.findViewById<TextView>(R.id.message)
        messageView.text = message
        setView(view)
    }

    companion object {
        /**
         * Make a standard toast that just contains a text view.
         *
         * @param context The context to use.  Usually your [Activity] object.
         * @param resId   The resource id of the string resource to use.
         */
        fun makeText(context: Activity, @StringRes resId: Int): ErrorToast {
            val toast = ErrorToast(context)
            toast.setMessage(context, resId)
            toast.duration = LENGTH_LONG
            toast.setGravity(Gravity.CENTER, 0, 0)
            return toast
        }

        /**
         * Make a standard toast that just contains a text view.
         *
         * @param context The context to use.  Usually your [Activity] object.
         * @param message The text to show.
         */
        fun makeText(context: Activity, message: String): ErrorToast {
            val toast = ErrorToast(context)
            toast.setMessage(context, message)
            toast.duration = LENGTH_LONG
            toast.setGravity(Gravity.CENTER, 0, 0)
            return toast
        }

        /**
         * Make a standard toast that just contains a text view.
         *
         * @param context The context to use.  Usually your [Activity] object.
         * @param resId   The resource id of the string resource to use.
         */
        fun makeShortText(context: Activity, @StringRes resId: Int): ErrorToast {
            val toast = ErrorToast(context)
            toast.setMessage(context, resId)
            toast.duration = LENGTH_SHORT
            toast.setGravity(Gravity.CENTER, 0, 0)
            return toast
        }

        /**
         * Make a standard toast that just contains a text view.
         *
         * @param context The context to use.  Usually your [Activity] object.
         * @param message The text to show.
         */
        fun makeShortText(context: Activity, message: String): ErrorToast {
            val toast = ErrorToast(context)
            toast.setMessage(context, message)
            toast.duration = LENGTH_SHORT
            toast.setGravity(Gravity.CENTER, 0, 0)
            return toast
        }
    }
}