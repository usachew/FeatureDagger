package com.usacheow.simpleapp.mainscreen.di

import com.usacheow.di.ActivityScope
import com.usacheow.di.DiProvider
import com.usacheow.simpleapp.mainscreen.BottomBarFragment
import com.usacheow.simpleapp.mainscreen.MainScreenActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [DiProvider::class],
    modules = [MainScreenModule::class]
)
interface MainScreenComponent {

    companion object {

        fun init(diProvider: DiProvider): MainScreenComponent {
            return DaggerMainScreenComponent.builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(activity: MainScreenActivity)

    fun inject(fragment: BottomBarFragment)
}