package com.servicetitan.mviexample.core

import java.util.*

abstract class Event {
    val id: UUID = UUID.randomUUID()
    val timestamp: Date = Date()
}