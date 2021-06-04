package com.b21cap0398.acnescan.utils.helper

import android.widget.ProgressBar
import android.widget.TextView

object ProgressBarOperator {
    private var progressBarValue = 0

    private var progressBar : ProgressBar? = null
    private var descriptionTextView : TextView? = null

    fun setProgressBarValue(num: Int) {
        progressBarValue = num
        progressBar?.progress = progressBarValue
    }

    fun setDescrtiptionTextView(text: String) {
        descriptionTextView?.text = text
    }

    fun assignProgressBar(pb: ProgressBar) {
        this.progressBar = pb
    }

    fun assignDescriptionTextView(tv: TextView) {
        this.descriptionTextView = tv
    }

    fun getProgressBarValue(): Int = progressBarValue
}