package com.example.newsfeedtask.widgets.inputs

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged

import com.example.newsfeedtask.databinding.ViewSearchInputBinding
import com.example.newsfeedtask.util.inflater

class SearchInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewSearchInputBinding.inflate(inflater, this, true)

    var onClearButtonClicked: (() -> Unit)? = null

    var onSearchInputChanged: ((String) -> Unit)? = null

    var text: String
        get() = binding.etInput.text.toString()
        set(value) = binding.etInput.setText(value)

    init {
        initUI()
    }

    private fun initUI() {
        with(binding) {
            imgClear.setOnClickListener {
                onClearButtonClicked?.invoke()
            }
            etInput.doOnTextChanged { text, _, _, _ ->
                onSearchInputChanged?.invoke(text.toString())
            }
        }
    }
}