package com.example.IN2000_prosjekt.model.Internett

import kotlinx.coroutines.flow.Flow

interface NetworkObserver {
    fun observer(): Flow<Status>
    enum class Status{
        Available, Unavailable
    }
}
