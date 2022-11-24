package com.example.myapplication

import android.net.Uri
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.robolectric.RobolectricTest
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

private class check(): TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
    }
}
@RobolectricTest
class ViewModelTest : BehaviorSpec() {

    private lateinit var mainthread: TestDispatcher

    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        mainthread = StandardTestDispatcher()
        Dispatchers.setMain(mainthread)
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        Dispatchers.resetMain()
    }

    private fun <T: Any> T.waitUntilIdle() {
        mainthread.scheduler.advanceUntilIdle()
    }

    init {
        Given("check") {

            When("chek") {
                val viewModel = TestViewModel()
                val a = Uri.parse("https://host.com")
                viewModel.testChange()

                mainthread.scheduler.advanceUntilIdle()

                And("sdf") {
                    viewModel.settUri(a)

                    Then("ct") {
                        viewModel.a shouldBe 10
                    }
                    Then("ct") {
                        a.toString() shouldBe "https://host.com"
                    }
                }
                Then("c") {
                    viewModel.a shouldBe 10
                }
                Then("secodn") {
                    a.host shouldBe "host.com"
                }
            }
        }
    }
}