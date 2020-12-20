package com.tahir.validateuser_lib

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tahir.validateuser_lib.helping.TestHelperEnums
import com.tahir.validateuser_lib.helping.Validations
import com.tahir.validateuser_lib.interfaces.Validationcallbacks
import com.tahir.validateuser_lib.models.UserInfo
import com.tahir.validateuser_lib.viewstate.DataState
import com.tahir.validateuser_lib.viewstate.SubmitStatus
import com.tahir.validateuser_lib.vm.MainActivityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class LibTests {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var data_observer: Observer<DataState<ResponseBody>>

    lateinit var mainacvm: MainActivityViewModel

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    var lifecycle: Lifecycle? = null


    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        mainacvm = MainActivityViewModel()


    }

    @Test
    fun dataPostSuccess() {
        val signal = CountDownLatch(1)
        var result: String? = null
        mainacvm.setStateEvent(
            SubmitStatus.submitUserData,
            "uv065guv",
            UserInfo("tahir zaidi", "scheme 33 karachi", "N/A", "Karachi", "75290", "Sindh", "PK")
        )
        data_observer = Observer<DataState<ResponseBody>> { dataState ->
            when (dataState) {

                is DataState.Success<ResponseBody> -> {
                    result = TestHelperEnums.SUCCESS.toString()
                    signal.countDown()
                }
                is DataState.Error -> {
                    result = TestHelperEnums.SUCCESS.toString()
                    signal.countDown()
                }

            }


        }
        mainacvm.dataState.observeForever(data_observer)

        signal.await()
        Assert.assertEquals(result, "SUCCESS")
    }

    // data validation checks
    @Test
    fun dataValidationSuccess() {

        var result: String? = null
        Validations.validateUserInputs(
            "tahir zaidi",
            "scheme 33 karachi",
            "N/A",
            "Karachi",
            "75290",
            "Sindh",
            "PK",
            object : Validationcallbacks {
                override fun onValidationError(error: String) {
                    result = TestHelperEnums.FAILED.toString()

                }

                override fun OnValidationSuccess(userInfo: UserInfo) {
                    result = TestHelperEnums.SUCCESS.toString()

                }


            })

        Assert.assertEquals(result, "SUCCESS")
    }

    @After
    @Throws(java.lang.Exception::class)
    fun tearDown() {
        if (mainacvm.dataState.hasActiveObservers()) {
            mainacvm.dataState.removeObserver(data_observer)
        }

    }
}