package com.sorokin.gifviewer

import android.app.Application
import com.sorokin.gifviewer.domain.di.domainModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.kodein.di.DI
import org.kodein.di.DIAware

@ExperimentalSerializationApi
class MainApplication : Application(), DIAware {

    override val di = DI.lazy {
        import(domainModule())
    }
}
