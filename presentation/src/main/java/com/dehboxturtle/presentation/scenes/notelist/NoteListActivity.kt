package com.dehboxturtle.presentation.scenes.notelist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dehboxturtle.presentation.R
import com.dehboxturtle.presentation.extensions.addFragment
import com.dehboxturtle.presentation.scenes.base.view.ABaseActivity

class NoteListActivity : ABaseActivity(R.layout.activity_layout_to_load_fragment) {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, NoteListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeActivity(savedInstanceState)
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.container, NoteListFragment.newInstance())
        }
    }

}