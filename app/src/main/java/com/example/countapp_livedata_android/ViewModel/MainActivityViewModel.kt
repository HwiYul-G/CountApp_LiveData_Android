package com.example.countapp_livedata_android.ViewModel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    // VM에 LiveData 생성
    private val _seconds = MutableLiveData<Int>()
    private val _finished = MutableLiveData<Boolean>()

    // getter method for seconds and finished var
    fun seconds() : LiveData<Int> {
        return _seconds
    }

    fun finished() : LiveData<Boolean> {
        return _finished
    }

    // Counter methd that uses CountDownTimer
    fun startCounter() {
        // you can change the millisInFuture value
        object : CountDownTimer(5000, 100) {

            override fun onTick(millisUntilFinished: Long) {
                val time = (millisUntilFinished / 1000)
                _seconds.value = time.toInt()
            }

            override fun onFinish() {
                _finished.value = true
            }
        }.start()
    }

}