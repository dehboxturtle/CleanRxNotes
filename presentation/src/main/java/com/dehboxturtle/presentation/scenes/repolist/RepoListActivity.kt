package com.dehboxturtle.presentation.scenes.repolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dehboxturtle.presentation.R
import com.dehboxturtle.presentation.extensions.addFragment
import com.dehboxturtle.presentation.extensions.enableToolbar
import com.dehboxturtle.presentation.scenes.base.view.ABaseActivity

class RepoListActivity : ABaseActivity(R.layout.activity_layout_to_load_fragment) {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, RepoListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure this is before calling super.onCreate
        setTheme(R.style.Base_Theme)
        super.onCreate(savedInstanceState)
        initializeActivity(savedInstanceState)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        enableToolbar()
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.container, RepoListFragment.newInstance())
        }
    }
}
