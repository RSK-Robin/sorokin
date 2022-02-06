package com.sorokin.gifviewer.utils

import android.app.Application
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun Fragment.viewModelFactory(creator: (Application) -> ViewModel): ViewModelProvider.Factory =
    FragmentFactory(requireActivity().application, creator)

private class FragmentFactory(
    private val application: Application,
    private val creator: (Application) -> ViewModel
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = creator(application) as T
}

private class BindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val binder: (View) -> T
) : ReadOnlyProperty<Any?, T>, LifecycleObserver {

    private var value: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { lifecycleOwner ->
            lifecycleOwner.lifecycle.addObserver(this)
        }
    }

    @Suppress("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        value = null
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>) = checkNotNull(
        value ?: binder(fragment.requireView()).also { value = it }
    )
}

fun <T : ViewBinding> Fragment.binding(
    binder: (View) -> T
): ReadOnlyProperty<Any?, T> = BindingDelegate(this, binder)
