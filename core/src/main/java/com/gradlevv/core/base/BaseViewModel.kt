package com.gradlevv.core.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.samcrow.core.util.Event
import com.gradlevv.core.util.NavigationCommand
import com.samcrow.core.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel(){

    private val navigationCommands = SingleLiveEvent<NavigationCommand>()
    private val navigationUpEvent = MutableLiveData<Event<Boolean>>()

    fun navigate(directions: NavDirections) {
        navigationCommands.postValue(NavigationCommand.To(directions))
    }

    fun navigate(@StringRes deepLinkRes: Int, directions: NavOptions? = null) {
        navigationCommands.postValue(NavigationCommand.ToDeppLink(deepLinkRes, directions))
    }

    fun navigationCommand():LiveData<NavigationCommand> = navigationCommands

    fun navigateUp(){
        navigationUpEvent.value = Event(true)
    }

    fun navigateUpEvent(): LiveData<Event<Boolean>> = navigationUpEvent
}