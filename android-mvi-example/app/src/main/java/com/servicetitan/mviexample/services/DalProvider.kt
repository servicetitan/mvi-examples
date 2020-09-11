package com.servicetitan.mviexample.services

import android.app.Application
import com.example.dal.STDal
import javax.inject.Inject

class DalProvider @Inject constructor(application: Application) {

    val stDalManager by lazy { STDal.init(application.baseContext) }
}