package com.gradlevv.ui.utils

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.StringDef
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION
)
@StringDef(
    Colors.colorPrimary,
    Colors.colorPrimaryDark,
    Colors.colorAccent,
    Colors.colorBackground,
    Colors.colorText,
    Colors.colorIconTint,
    Colors.colorWhite,
    Colors.colorTransparent,
    Colors.colorStatusBar,
    Colors.colorNavBar
)
annotation class Colors {
    companion object {

        const val colorPrimary = "colorPrimary"
        const val colorPrimaryDark = "colorPrimaryDark"
        const val colorBackground = "colorBackground"

        const val colorAccent = "colorAccent"

        const val colorText = "colorText"
        const val colorIconTint = "colorIconTint"

        const val colorWhite = "colorWhite"

        const val colorTransparent = "colorTransparent"

        const val colorStatusBar = "colorStatusBar"
        const val colorNavBar = "colorNavBar"
    }
}


object ThemeHandler {

    sealed class Theme(val value: String) {
        object Day : Theme("day")
        object Night : Theme("night")
    }

    fun setTheme(theme: Theme) {
        this.theme.value = theme
    }

    private val theme = MutableLiveData<Theme>(Theme.Day)
    val themeObservable: LiveData<Theme> = theme

    val isDark: Boolean
        get() = theme.value?.value == Theme.Night.value

    private val dayColors: HashMap<String, Int> = hashMapOf(
        Colors.colorPrimary to Day.colorPrimary,
        Colors.colorTransparent to Day.colorTransparent,
        Colors.colorPrimaryDark to Day.colorPrimaryDark,
        Colors.colorBackground to Day.colorBackground,
        Colors.colorAccent to Day.colorAccent,
        Colors.colorText to Day.colorText,
        Colors.colorIconTint to Day.colorIconTint,
        Colors.colorWhite to Day.colorWhite,
        Colors.colorStatusBar to Day.colorStatusBar,
        Colors.colorNavBar to Day.colorNavBar
    )

    private val nightColors: HashMap<String, Int> = hashMapOf(
        Colors.colorPrimary to Night.colorPrimary,
        Colors.colorTransparent to Night.colorTransparent,
        Colors.colorPrimaryDark to Night.colorPrimaryDark,
        Colors.colorBackground to Night.colorBackground,
        Colors.colorAccent to Night.colorAccent,
        Colors.colorText to Night.colorText,
        Colors.colorIconTint to Night.colorIconTint,
        Colors.colorWhite to Night.colorWhite,
        Colors.colorStatusBar to Night.colorStatusBar,
        Colors.colorNavBar to Night.colorNavBar
    )

    private object Day {

        @ColorInt
        val colorPrimary = Color.parseColor("#2E74E5")

        @ColorInt
        val colorTransparent = Color.argb(0, 0, 0, 0)

        @ColorInt
        val colorPrimaryDark = Color.parseColor("#141414")

        @ColorInt
        val colorBackground = Color.parseColor("#ffffff")

        @ColorInt
        val colorAccent = Color.parseColor("#03DAC5")

        @ColorInt
        val colorText = Color.parseColor("#363636")

        @ColorInt
        val colorIconTint = Color.parseColor("#A2A2A2")

        @ColorInt
        val colorWhite = Color.parseColor("#FFFFFF")

        @ColorInt
        val colorStatusBar = Color.parseColor("#F7F7FC")

        @ColorInt
        val colorNavBar = Color.parseColor("#F7F8FD")
    }

    private object Night {

        @ColorInt
        val colorPrimary = Color.parseColor("#2E74E5")

        @ColorInt
        val colorTransparent = Color.argb(0, 0, 0, 0)

        @ColorInt
        val colorPrimaryDark = Color.parseColor("#141414")

        @ColorInt
        val colorBackground = Color.parseColor("#141414")

        @ColorInt
        val colorAccent = Color.parseColor("#03DAC5")

        @ColorInt
        val colorText = Color.parseColor("#fafafa")

        @ColorInt
        val colorIconTint = Color.parseColor("#A2A2A2")

        @ColorInt
        val colorWhite = Color.parseColor("#FFFFFF")

        @ColorInt
        val colorStatusBar = Color.parseColor("#F7F7FC")

        @ColorInt
        val colorNavBar = Color.parseColor("#F7F8FD")
    }


    @JvmStatic
    @ColorInt
    fun getColor(@Colors key: String): Int {
        return when (theme.value) {
            Theme.Day -> {
                dayColors[key]!!
            }
            Theme.Night -> {
                nightColors[key]!!
            }
            null -> {
                dayColors[key]!!
            }
        }
    }

    fun getColorState(@Colors key: String): ColorStateList {
        return when (theme.value) {
            Theme.Day -> {
                ColorStateList.valueOf(dayColors[key]!!)
            }
            Theme.Night -> {
                ColorStateList.valueOf(nightColors[key]!!)
            }
            null -> {
                ColorStateList.valueOf(dayColors[key]!!)
            }
        }
    }

    fun getColorStateWithAlpha(@Colors key: String, alphaPercent: Int): ColorStateList {
        return ColorStateList.valueOf(getColorWithAlpha(key, alphaPercent))
    }

    @ColorInt
    fun getColorWithAlpha(@Colors key: String, alphaPercent: Int): Int {
        val color = getColor(key)

        return Color.argb(
            ((alphaPercent / 100f) * 255f).toInt(),
            Color.red(color),
            Color.green(color),
            Color.blue(color)
        )
    }

}