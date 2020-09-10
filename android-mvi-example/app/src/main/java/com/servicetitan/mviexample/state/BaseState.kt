package com.servicetitan.mviexample.state

import java.util.*

open class BaseState {
    val id: UUID = UUID.randomUUID()
    val timestamp: Date = Date()

    object None: BaseState()

    fun log() = "Log State: [${id} - ${timestamp}] -> ${this::class.java.simpleName}"
}