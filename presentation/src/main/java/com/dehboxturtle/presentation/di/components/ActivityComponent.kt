package com.dehboxturtle.presentation.di.components

import android.app.Activity
import com.dehboxturtle.presentation.di.PerActivity
import com.dehboxturtle.presentation.di.modules.ActivityModule
import com.dehboxturtle.presentation.scenes.notelist.NoteListFragment
import com.dehboxturtle.presentation.scenes.repo.RepoFragment
import com.dehboxturtle.presentation.scenes.repolist.RepoListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }

    //region Inject
    fun inject(fragment: RepoListFragment)

    fun inject(fragment: RepoFragment)

    fun inject(fragment: NoteListFragment)
    //endregion

}
