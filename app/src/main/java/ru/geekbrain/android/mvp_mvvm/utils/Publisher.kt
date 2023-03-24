package ru.geekbrain.android.mvp_mvvm.utils

import android.os.Handler

private class Subscriber<T>(
    private val handler: Handler,
    val TAG: String?,
    private val callback: (T?) -> Unit
){
    fun invoke(value : T?){
        handler.post{
            callback.invoke(value)
        }
    }
}


//Проблемы
// 1) Toast показывается после поворота
// 2) Поддержка многопоточности
class Publisher<T>(val isSingle: Boolean = false) {
    private var subscribers: MutableSet<Subscriber<T?>> = mutableSetOf()
    var value: T? = null
        private set

    private  var hasFirstValue = false

    fun subscribe(handler: Handler, TAG: String?=null, callback:(T?) -> Unit,  ){
        val subscriber = Subscriber(handler, TAG = null, callback )
        this.subscribers.add( subscriber)
        if(hasFirstValue) {
                subscriber.invoke(value)         //send last value to The new subscriber
        }
    }

    fun unSubscribe(tag: String){
        this.subscribers.forEach {
            if(it.TAG == tag)
                subscribers.remove(it)
        }
    }

    fun post(value: T){
        if(!isSingle) {
            this.value = value
        }
        subscribers.forEach {
            it.invoke(value)
        }
    }

    fun unSubscribeAll(){
        subscribers.clear()
    }

}


