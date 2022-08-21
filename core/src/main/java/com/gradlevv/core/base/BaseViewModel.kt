package com.gradlevv.core.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.gradlevv.core.util.Event
import com.gradlevv.core.util.NavigationModel
import com.gradlevv.core.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel(){

    private val navigationCommands = SingleLiveEvent<NavigationModel>()
    private val navigationUpEvent = MutableLiveData<Event<Boolean>>()

    protected val errorMessage = SingleLiveEvent<String>()

    fun navigate(directions: NavDirections) {
        navigationCommands.postValue(NavigationModel.To(directions))
    }

    fun navigate(@StringRes deepLinkRes: Int, directions: NavOptions? = null) {
        navigationCommands.postValue(NavigationModel.ToDeppLink(deepLinkRes, directions))
    }

    fun navigationCommand():LiveData<NavigationModel> = navigationCommands

    fun navigateUp(){
        navigationUpEvent.value = Event(true)
    }

    fun navigateUpEvent(): LiveData<Event<Boolean>> = navigationUpEvent

    fun errorMessage(): LiveData<String> = errorMessage
}