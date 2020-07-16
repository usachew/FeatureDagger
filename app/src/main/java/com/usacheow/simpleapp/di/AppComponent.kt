package com.usacheow.simpleapp.di

import com.usacheow.coredata.di.CoreDataModule
import com.usacheow.di.DiProvider
import com.usacheow.simpleapp.SimpleApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        MediatorModule::class,
        CoreDataModule::class
    ]
)
interface AppComponent : DiProvider {

    companion object {

        fun init(app: SimpleApp): AppComponent {
            val coreModule = CoreModule(app)

            return DaggerAppComponent
                .builder()
                .coreModule(coreModule)
                .build()
        }
    }

    fun inject(app: SimpleApp)
}