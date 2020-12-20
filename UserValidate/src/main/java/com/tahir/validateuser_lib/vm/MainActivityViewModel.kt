package com.tahir.validateuser_lib.vm


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahir.validateuser_lib.configs.App
import com.tahir.validateuser_lib.models.UserInfo
import com.tahir.validateuser_lib.repository.AppRepository
import com.tahir.validateuser_lib.viewstate.DataState
import com.tahir.validateuser_lib.viewstate.SubmitStatus
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * MainAcivity ViewModel.
 *
 * This class is a view model which will be tied to the lifecycle of UserValidationActivity.
 * @constructor DI and will set state from user's perfomed action in our case this will be an API call.
 */
class MainActivityViewModel : ViewModel() {
    private val _dataState: MutableLiveData<DataState<ResponseBody>> = MutableLiveData()

    @Inject
    lateinit var dbrepo: AppRepository

    // data state observer with getter
    val dataState: MutableLiveData<DataState<ResponseBody>>
        get() = _dataState

    init {
        App.app.appLevelComponent.inject(this)

    }


    /**
     * Method for setting up state
     * @param mainStateEvent - instance of sealed FetchStatus class - MainActSM.kt file.
     */
    fun setStateEvent(mainStateEvent: SubmitStatus, id: String, userInfo: UserInfo) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is SubmitStatus.submitUserData -> {
                    //  posting user data
                    dbrepo.postUserData(userInfo, id)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                SubmitStatus.None -> {
                    // For now nothing...
                }
            }
        }
    }

}


