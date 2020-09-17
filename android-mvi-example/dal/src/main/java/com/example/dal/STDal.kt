package com.example.dal

import android.content.Context
import com.example.dal.manager.DalManager
import com.example.dal.manager.STDalManager

object STDal {

    fun init(context: Context): STDalManager = DalManager().apply { init(context) }
}