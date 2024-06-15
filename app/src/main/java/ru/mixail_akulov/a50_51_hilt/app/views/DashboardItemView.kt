package ru.mixail_akulov.a50_51_hilt.app.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import ru.mixail_akulov.a50_51_hilt.app.model.boxes.entities.Box
import ru.mixail_akulov.a50_51_hilt.app.R
import ru.mixail_akulov.a50_51_hilt.app.databinding.PartDashboardItemBinding

class DashboardItemView @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.dashboardItemStyle,
    defStyleRes: Int = R.style.DefaultDashboardItemStyle
) : FrameLayout(context, attributesSet, defStyleAttr, defStyleRes) {

    private val binding: PartDashboardItemBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_dashboard_item, this, true)
        binding = PartDashboardItemBinding.bind(this)
        parseAttributes(attributesSet, defStyleAttr, defStyleRes)
    }

    fun setBox(box: Box) {
        val colorName = box.colorName
        val boxTitle = context.getString(R.string.box_title, colorName)
        setupTitle(boxTitle)
        setupColors(box.colorValue)
    }

    private fun parseAttributes(attributesSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val defaultColor = ContextCompat.getColor(context, R.color.defaultDashboardColor)
        val defaultTitle = "No Title"

        val color: Int
        val title: String
        if (attributesSet != null) {
            val typedArray = context.obtainStyledAttributes(attributesSet, R.styleable.DashboardItemView, defStyleAttr, defStyleRes)
            color = typedArray.getColor(R.styleable.DashboardItemView_color, defaultColor)
            title = typedArray.getString(R.styleable.DashboardItemView_title) ?: defaultTitle
            typedArray.recycle()
        } else {
            color = defaultColor
            title = defaultTitle
        }
        setupColors(color)
        setupTitle(title)
    }

    private fun setupTitle(title: String) {
        binding.titleTextView.text = title
    }

    private fun setupColors(strokeColor: Int) {
        val bgColor = getBackgroundColor(strokeColor)

        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.color = ColorStateList.valueOf(bgColor)
        backgroundDrawable.setStroke(resources.getDimensionPixelSize(R.dimen.dashboard_item_stroke_width), strokeColor)
        backgroundDrawable.cornerRadius = resources.getDimensionPixelSize(R.dimen.dashboard_item_corner_radius).toFloat()

        binding.titleTextView.setTextColor(strokeColor)
        background = RippleDrawable(ColorStateList.valueOf(Color.BLACK), backgroundDrawable, null)
    }

    companion object {
        fun getBackgroundColor(color: Int): Int {
            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)
            return Color.argb(64, red, green, blue)
        }
    }
}