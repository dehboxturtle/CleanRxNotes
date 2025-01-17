package com.dehboxturtle.presentation.di.modules

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.dehboxturtle.presentation.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Dagger module that provides activity.
 */
@Module
class ActivityModule {

    @Provides
    @PerActivity
    internal fun provideActivity(activity: Activity) = activity as AppCompatActivity

}
