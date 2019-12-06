package com.chen.functionmanager

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import java.util.*
import kotlin.collections.ArrayList


/**
 * EventManager管理--------------------start---------------------------------
 */
private val mEventManagerList by lazy {
    ArrayList<EventManager>()
}

fun EventManager.addEventManager() {
    mEventManagerList.add(this)
}

fun EventManager.removeEventManager() {
    mEventManagerList.remove(this)
}

fun LifecycleOwner.newEventManager(name: String = this::class.java.name): EventManager {
    val eventManager = EventManager(name).apply {
        addEventManager()
    }
    lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                eventManager.clearAll()
                eventManager.removeEventManager()
            }
        }
    })
    return eventManager
}

/**
 * EventManager管理--------------------end---------------------------------
 */


const val DEFAULT_ACTION = "default_action"

/**
 *  Created by zzw on 2019-07-31
 *  Des: 一个和生命周期绑定的事件分发类入口类,具体怎么实现可以内部修改.
 *      当前采用比较简单的观察者模式实现，注意线程问题，回调在当前线程。
 */
class EventManager(private val name: String) {

    private val mEventObservable: EventObservable by lazy {
        EventObservable()
    }

    fun post(action: String = DEFAULT_ACTION, obj: Any? = null, from: String) {
        mEventObservable.notify(from, action, obj)
    }

    fun bind(lifecycleOwner: LifecycleOwner, callBack: (action: String, obj: Any?) -> Unit = { _, _ -> }, actions: Array<String>): EventManager {
        val from = lifecycleOwner::class.java.name

        val eventObservers = actions.map {
            EventObservable.EventObserver(from, callBack, it).apply {
                Log.d("EventManager", "bind-->$this" +
                        "\neventManagerName=$name")
                mEventObservable.addObserver(this)
            }
        }

        lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    eventObservers.forEach {
                        Log.d("EventManager", "unbind-->$it" +
                                "\neventManagerName=$name")
                        mEventObservable.deleteObserver(it)
                    }
                }
            }
        })
        return this
    }

    fun clearAll() {
        mEventObservable.deleteObservers()
    }


    private class EventObservable : Observable() {
        fun notify(from: String, action: String, obj: Any?) {
            setChanged()
            val event = Event(from, action, obj)
            Log.d("EventManager", "\npost-->$event")
            notifyObservers(event)
        }

        private class Event(
                //那个地方发送的
                val from: String,
                val postAction: String,
                val data: Any?) {
            override fun toString(): String {
                return "Event{\nfrom=${from}" +
                        "\npostAction=${postAction}" +
                        "\nobj=${data.toString()}" +
                        "\nthread=${Thread.currentThread().name}\n}"
            }
        }

        class EventObserver(
                //那个地方注册的
                val from: String,
                val callBack: (action: String, obj: Any?) -> Unit = { _, _ -> },
                val receiveAction: String) : Observer {
            override fun update(observable: Observable?, obj: Any?) {
                (obj as Event).apply {
                    if (receiveAction == postAction) {
                        callBack(postAction, data)
                    }
                }
            }

            override fun toString(): String {
                return "EventObserver{\nfrom=${from}" +
                        "\nreceiveAction=${receiveAction}" +
                        "\nthread=${Thread.currentThread().name}\n}"
            }
        }
    }

}