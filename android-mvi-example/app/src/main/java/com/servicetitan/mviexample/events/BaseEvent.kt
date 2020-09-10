package com.servicetitan.mviexample.events

import java.util.*

abstract class BaseEvent {
    val id: UUID = UUID.randomUUID()
    val timestamp: Date = Date()

    fun log() = "Log Event: [${id} - ${timestamp}] -> ${this::class.java.simpleName}"
}