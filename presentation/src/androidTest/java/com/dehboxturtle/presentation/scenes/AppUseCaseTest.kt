package com.dehboxturtle.presentation.scenes

import com.dehboxturtle.domain.model.Repo
import com.dehboxturtle.presentation.extensions.intentsTestRule
import com.dehboxturtle.presentation.extensions.robot
import com.dehboxturtle.presentation.scenes.repolist.RepoListActivity
import org.junit.Rule
import org.junit.Test

class AppUseCaseTest {

    @get:Rule
    val activityRule = intentsTestRule<RepoListActivity>()

    @Test
    fun showDataAndOpenRepo() {
        val repos = listOf(
            Repo(
                1,
                "name",
                "description",
                "http://repo.com",
                false,
                "userName"
            )
        )

        activityRule.robot {
            data(repos) {
                clickAtPosition(0) {
                    data(repos[0]) {
                        clickOnMenuLink()
                    }
                }
            }
        }
    }

}