package com.zj.budgetingapp

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding

inline fun<reified BindingT: ViewBinding> FragmentActivity.viewBinding(crossinline inflate:(LayoutInflater)->BindingT) =
    object : Lazy<BindingT> {
        private var cache:BindingT? = null
        private val observer = LifecycleEventObserver { source, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                cache = null
            }
        }
        override val value: BindingT
            get() = cache?:inflate(layoutInflater).also {
                cache = it
                lifecycle.addObserver(observer)
            }

        override fun isInitialized() = cache!=null

    }

inline fun<reified BindingT:ViewBinding> Fragment.viewBinding(crossinline inflate:(LayoutInflater)->BindingT) =
    this.activity?.viewBinding(inflate)