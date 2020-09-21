package com.servicetitan.mviexample.events

import java.util.*

abstract class BaseEvent {
    val id: UUID = UUID.randomUUID()
    val timestamp: Date = Date()
    open var eventType: String = ""

    fun log() = "Log Event: [${id} - ${timestamp}] -> ${eventType}-${this::class.java.simpleName}"
}