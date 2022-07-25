package com.example.newsfeedtask.widgets.dialogs

import android.app.Dialog
import android.content.Context
import com.example.newsfeedtask.R
import com.example.newsfeedtask.databinding.DialogErrorBinding

class ErrorDialog private constructor(
    context: Context
) : Dialog(context, R.style.AlertDialog) {

    private val mDialog: ErrorDialog
    private val binding = DialogErrorBinding.inflate(layoutInflater)


    init {
        setContentView(binding.root)
        mDialog = this
    }

    fun setMessage(message: String) {
        binding.txtMessage.text = message
    }

    fun setCloseButton(title: String, onClickListener: (Dialog) -> Unit) {
        binding.btnClose.text = title
        binding.btnClose.setOnClickListener { onClickListener(mDialog) }
    }

    class Builder(
        context: Context
    ) {
        private var dialog = ErrorDialog(context)

        fun setCancelable(cancelable: Boolean): Builder {
            dialog.setCancelable(cancelable)
            return this
        }

        fun setMessage(message: String): Builder {
            dialog.setMessage(message)
            return this
        }

        fun setCloseButton(title: String = "Close", onClickListener: (Dialog) -> Unit): Builder {
            dialog.setCloseButton(title, onClickListener)
            return this
        }

        fun show(): ErrorDialog {
            dialog.show()
            return dialog
        }

        init {
            dialog.setCancelable(false)
        }
    }
}