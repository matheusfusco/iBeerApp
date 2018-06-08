package com.fiap.matheusfusco.matheusfusco.base

interface BaseView {

    enum class ProgressType {
        PROGRESS_DIALOG
    }


    fun showProgress(type: ProgressType)
    fun hideProgress()
    fun onConnectionFailed()
    fun onAuthError()
}