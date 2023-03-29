package com.example.alankituniverse.util.helper

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveData<T>() : MutableLiveData<T>() {
    constructor(value: T) : this() {
        super.postValue(value)
    }

    private val mPending = AtomicBoolean(false)

    fun singleObserver(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, {
            if (mPending.compareAndSet(true, false)) observer.onChanged(it)
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }

}