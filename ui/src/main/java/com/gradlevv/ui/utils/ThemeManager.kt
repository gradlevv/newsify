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
    Colors.colorIconTint
)
annotation class Colors {
    companion object {

        const val colorPrimary = "colorPrimary"
        const val colorPrimaryDark = "colorPrimaryDark"
        const val colorBackground  = "colorBackground"

        const val colorAccent = "colorAccent"

        const val colorText = "colorText"
        const val colorIconTint = "colorIconTint"

        const val colorTransparent = "colorTransparent"

    }
}


object ThemeManager {

    sealed class Theme(val value: String) {
        object Day : Theme("day")
        object Night : Theme("night")
        object Amoled : Theme("amoled")
    }

    fun setTheme(theme: Theme) {
        this.theme.value = theme
    }

    private val theme = MutableLiveData<Theme>(Theme.Day)
    val themeObservable: LiveData<Theme> = theme

    val isDark: Boolean
        get() = theme.value?.value == Theme.Night.value || theme.value?.value == Theme.Amoled.value

    private val dayColors: HashMap<String, Int> = hashMapOf(
        Colors.colorPrimary to Day.colorPrimary,
        Colors.colorTransparent to Day.colorTransparent,
        Colors.colorPrimaryDark to Day.colorPrimaryDark,
        Colors.colorBackground to Day.colorBackground,
        Colors.colorAccent to Day.colorAccent,
        Colors.colorText to Day.colorText,
        Colors.colorIconTint to Day.colorIconTint
        )

    private val nightColors: HashMap<String, Int> = hashMapOf(
        Colors.colorPrimary to Night.colorPrimary,
        Colors.colorTransparent to Night.colorTransparent,
        Colors.colorPrimaryDark to Night.colorPrimaryDark,
        Colors.colorBackground to Night.colorBackground,
        Colors.colorAccent to Night.colorAccent,
        Colors.colorText to Night.colorText,
        Colors.colorIconTint to Night.colorIconTint
    )

    private val amoledColors: HashMap<String, Int> = hashMapOf(
        Colors.colorPrimary to Amoled.colorPrimary,
        Colors.colorTransparent to Amoled.colorTransparent,
        Colors.colorPrimaryDark to Amoled.colorPrimaryDark,
        Colors.colorBackground to Amoled.colorBackground,
        Colors.colorAccent to Amoled.colorAccent,
        Colors.colorText to Amoled.colorText,
        Colors.colorIconTint to Amoled.colorIconTint
    )

    private object Day {

        @ColorInt
        val colorPrimary = Color.parseColor("#141414")

        @ColorInt
        val colorTransparent = Color.argb(0, 0, 0, 0)

        @ColorInt
        val colorPrimaryDark = Color.parseColor("#141414")

        @ColorInt
        val colorBackground  = Color.parseColor("#ffffff")

        @ColorInt
        val colorAccent = Color.parseColor("#03DAC5")

        @ColorInt
        val colorText = Color.parseColor("#363636")

        @ColorInt
        val colorIconTint = Color.parseColor("#A2A2A2")

    }

    private object Night {

        @ColorInt
        val colorPrimary = Color.parseColor("#141414")

        @ColorInt
        val colorTransparent = Color.argb(0, 0, 0, 0)

        @ColorInt
        val colorPrimaryDark = Color.parseColor("#141414")

        @ColorInt
        val colorBackground  = Color.parseColor("#141414")

        @ColorInt
        val colorAccent = Color.parseColor("#03DAC5")

        @ColorInt
        val colorText = Color.parseColor("#fafafa")

        @ColorInt
        val colorIconTint = Color.parseColor("#A2A2A2")
    }

    private object Amoled {

        @ColorInt
        val colorPrimary = Color.parseColor("#000000")

        @ColorInt
        val colorTransparent = Color.argb(0, 0, 0, 0)

        @ColorInt
        val colorPrimaryDark = Color.parseColor("#000000")

        @ColorInt
        val colorBackground  = Color.parseColor("#000000")

        @ColorInt
        val colorAccent = Color.parseColor("#E50379")

        @ColorInt
        val colorText = Color.parseColor("#fafafa")

        @ColorInt
        val colorIconTint = Color.parseColor("#A2A2A2")
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
            Theme.Amoled -> {
                amoledColors[key]!!
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
            Theme.Amoled -> {
                ColorStateList.valueOf(amoledColors[key]!!)
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