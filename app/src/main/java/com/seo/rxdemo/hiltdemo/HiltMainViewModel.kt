package com.seo.rxdemo.hiltdemo

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.seo.rxdemo.HttpDemoActivity
import com.seo.rxdemo.htt.DeviceManager
import com.seo.rxdemo.htt.UserRepository
import com.seo.rxdemo.htt.req.ReqGeneralLoginParam
import com.seo.rxdemo.htt.req.ReqUserDetailsParam
import com.seo.rxdemo.htt.res.ResUserDetails
import com.seo.rxdemo.util.Cipher
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject


class HiltMainViewModel
@ViewModelInject constructor(val retrofit: Retrofit, val deviceManager: DeviceManager,
                             @Assisted private val savedStateHandle:SavedStateHandle): ViewModel() {

    val userRepository = retrofit.create(UserRepository::class.java)
    private val disposables = CompositeDisposable()

    companion object{
        private const val TAG = "HiltMainViewModel";
    }

    val profileData = MutableLiveData<ResUserDetails>()
    val requestState = MutableLiveData<RequestState>()


    fun getUserProfile(uid:String){

        val subscribe = userRepository.userDetails(
                ReqUserDetailsParam(
                    uid.Cipher(),
                    deviceManager.uuid,
                    deviceManager.lang
                )
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                Log.d(TAG, "getUserProfile: doOnSubscribe =====>")
                requestState.value = RequestState.START

            }
            .doAfterTerminate {
                Log.d(TAG, "getUserProfile: doAfterTerminate ====>")
                requestState.value = RequestState.END

            }
            .subscribe({

                profileData.value = it

            }) { error ->
                Log.d(TAG, "getUserProfile: error : ${error}")
            }
        disposables.add(subscribe)

    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

enum class RequestState{
    START,END
}