package com.example.newsfeedtask.widgets.toolbar

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.newsfeedtask.databinding.ViewToolbarBinding
import com.example.newsfeedtask.util.drawableCompat
import com.example.newsfeedtask.util.inflater

class Toolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val binding = ViewToolbarBinding.inflate(inflater, this, true)

    var title: String = ""
        set(value) {
            field = value
            binding.txtTitle.text = field
        }

    @DrawableRes
    var titleDrawable: Int = 0
        set(value) {
            field = value
            binding.txtTitle.isVisible = field == 0
            binding.imgTitle.isVisible = field != 0
            binding.imgTitle.setImageDrawable(context.drawableCompat(field))
        }

    @DrawableRes
    var leftIcon: Int = 0
        set(value) {
            field = value
            binding.ivLeft.isVisible = field != 0
            binding.ivLeft.setImageDrawable(context.drawableCompat(field))
        }

    @DrawableRes
    var rightIcon: Int = 0
        set(value) {
            field = value
            binding.ivRight.isVisible = field != 0
            binding.ivRight.setImageDrawable(context.drawableCompat(field))
        }

    var onSearchInputChanged: ((String) -> Unit)? = null

    init {
        initSearchInput()
    }

    fun setOnLeftIconClicked(onClicked: () -> Unit) {
        binding.flLeftButton.setOnClickListener {
            onClicked()
        }
    }

    fun setOnRightIconClicked(onClicked: () -> Unit) {
        binding.flRightButton.setOnClickListener {
            onClicked()
        }
    }

    fun setState(state: State) {
        with(binding) {
            groupDefault.isVisible = state is State.Default
            searchInput.isVisible = state is State.Search
            if (state is State.Default) {
                searchInput.text = ""
            }
        }
    }

    private fun initSearchInput() {
        with(binding.searchInput) {
            onClearButtonClicked = {
                setState(State.Default)
            }
            onSearchInputChanged = {
                this@Toolbar.onSearchInputChanged?.invoke(it)
            }
        }
    }

    sealed class State {
        object Search : State()
        object Default : State()
    }

}