package ru.geekbrain.android.mvp_mvvm.utils

/*
interface Subscriber<T> {
    fun post(value: T?)
}
Короче запись ниже
*/

private typealias Subscriber<T> = (T?) -> Unit

//Проблемы
// 1) Toast показывается после поворота
// 2) Поддержка многопоточности
class Publisher<T> {
    private var subscribers: MutableSet<Subscriber<T>> = mutableSetOf()
    var value: T? = null
        private set

    private  var hasFirstValue = false

    fun subscribe(subscriber: Subscriber<T>){
        this.subscribers.add( subscriber)
        if(hasFirstValue) {
            subscriber.invoke(value)         //send last value to The new subscriber
        }
    }

    fun post(value: T){
        this.value = value
        subscribers.forEach{it.invoke(value)}
    }

    fun unSubscribe(subscriber: Subscriber<T>){
       subscribers.remove(subscriber)
    }

    fun unSubscribeAll(){
        subscribers.clear()
    }

}


