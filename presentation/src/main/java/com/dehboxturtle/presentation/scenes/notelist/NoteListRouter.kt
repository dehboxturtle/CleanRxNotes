package com.dehboxturtle.presentation.scenes.notelist

import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class NoteListRouter
@Inject internal constructor(private val activity: AppCompatActivity) {

    fun routeToSample() {
        //activity.startActivity(SampleActivity.newIntent(activity.applicationContext))
    }

}