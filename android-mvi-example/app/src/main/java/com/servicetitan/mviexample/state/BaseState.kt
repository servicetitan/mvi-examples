package com.servicetitan.mviexample.state

import java.util.*

open class BaseState {
    val id: UUID = UUID.randomUUID()
    val timestamp: Date = Date()
    open var stateType: String = ""

    object None: BaseState()
    fun log() = "Log State: [${id} - ${timestamp}] -> $stateType-H${this::class.java.simpleName}"
}