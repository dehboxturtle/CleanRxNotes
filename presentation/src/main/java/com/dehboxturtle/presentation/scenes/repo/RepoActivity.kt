package com.dehboxturtle.presentation.scenes.repo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dehboxturtle.presentation.R
import com.dehboxturtle.presentation.extensions.addFragment
import com.dehboxturtle.presentation.extensions.enableToolbar
import com.dehboxturtle.presentation.extensions.getLongExtra
import com.dehboxturtle.presentation.extensions.getStringExtra
import com.dehboxturtle.presentation.scenes.base.view.ABaseActivity
import io.reactivex.rxjava3.subjects.PublishSubject

class RepoActivity : ABaseActivity(R.layout.activity_layout_to_load_fragment) {

    companion object {
        private const val EXTRA_REPO_ID = "extra_repo_id"
        private const val EXTRA_REPO_NAME = "extra_repo_name"
        private const val EXTRA_USER_NAME = "extra_user_name"

        fun newIntent(context: Context, repoId: Long, repoName: String, userName: String): Intent =
            Intent(context, RepoActivity::class.java).apply {
                putExtra(EXTRA_REPO_ID, repoId)
                putExtra(EXTRA_REPO_NAME, repoName)
                putExtra(EXTRA_USER_NAME, userName)
            }
    }

    // Properties
    private val repoId: Long by lazy { getLongExtra(EXTRA_REPO_ID) }
    private val repoName: String by lazy { getStringExtra(EXTRA_REPO_NAME) }
    private val userName: String by lazy { getStringExtra(EXTRA_USER_NAME) }

    // Intent
    internal val intentActionLink = PublishSubject.create<Unit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeActivity(savedInstanceState)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        enableToolbar(true, repoName)
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            addFragment(R.id.container, RepoFragment.newInstance(repoId, repoName, userName))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.repo_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_link) {
            intentActionLink.onNext(Unit)
        }
        return super.onOptionsItemSelected(item)
    }

}
