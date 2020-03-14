package com.kapmayn.featurehello.di

import com.kapmayn.core.provider.DiProvider
import com.kapmayn.di.FeatureScope
import com.kapmayn.featurehello.presentation.activity.HelloActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [DiProvider::class],
    modules = [PresentationModule::class, DataModule::class]
)
interface HelloComponent {

    companion object {

        fun init(diProvider: DiProvider): HelloComponent {
            return DaggerHelloComponent.builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(activity: HelloActivity)
}