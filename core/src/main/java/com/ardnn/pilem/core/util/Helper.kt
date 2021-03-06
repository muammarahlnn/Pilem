package com.ardnn.pilem.core.util

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.ardnn.pilem.core.BuildConfig
import com.ardnn.pilem.core.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout

object Helper {

    private const val IMG_URL = BuildConfig.IMG_URL_TMDB

    fun equalingEachTabWidth(tabLayout: TabLayout) {
        val slidingTab: ViewGroup = tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabLayout.tabCount) {
            val tab: View = slidingTab.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = 1f
            tab.layoutParams = layoutParams
        }
    }

    fun setImageGlide(context: Context, url: String, imageView: ImageView) {
        if (url.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_error)
        } else {
            Glide.with(context)
                .load(url)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_error))
                .into(imageView)
        }
    }

    fun getPosterTMDB(posterUrl: String): String =
        "${IMG_URL}w500$posterUrl"

    fun getWallpaperTMDB(wallpaperUrl: String): String =
        "${IMG_URL}w780$wallpaperUrl"

    fun setTextString(text: String): String =
        if (text.isEmpty()) "-" else text

    fun setTextFloat(num: Float): String =
        if (num == 0f) "-" else num.toString()

    fun setTextYear(date: String): String =
        if (date.isEmpty()) "-" else date.substring(0, 4)

    fun setTextDate(date: String): String =
        if (date.isEmpty()) "-" else convertToDate(date)

    private fun convertToDate(date: String): String {
        if (date.isEmpty()) return "-"

        val months = listOf("",
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
        )

        val splittedDate = date.split("-") // [year, month, day]
        val year = splittedDate[0]
        val month = months[splittedDate[1].toInt()]
        var day = splittedDate[2]

        // remove leading zeros
        if (day[0] == '0') {
            day = day.substring(1)
        }

        return "$day $month, $year"
    }
}