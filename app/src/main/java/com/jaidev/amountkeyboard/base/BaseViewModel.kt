package com.jaidev.amountkeyboard.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaidev.amountkeyboard.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val bus: SingleLiveEvent<BaseEvent> = SingleLiveEvent()

    fun sendEvent(event: BaseEvent) {
        bus.postValue(event)
    }

    //Make network call
    public fun launch(
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                sendEvent(ErrorEvent(e.message ?: ""))
            }
        }
    }
}