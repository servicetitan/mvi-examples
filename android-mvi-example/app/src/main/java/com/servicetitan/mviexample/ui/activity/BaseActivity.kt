package com.servicetitan.mviexample.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.servicetitan.mviexample.processors.BaseProcessor
import com.servicetitan.mviexample.ui.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<T: BaseProcessor<*, *>>: AppCompatActivity() {

    @Inject
    lateinit var eventProcessor: T

    protected val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        eventProcessor.dispose()
    }

    abstract fun onViewCreated()
}