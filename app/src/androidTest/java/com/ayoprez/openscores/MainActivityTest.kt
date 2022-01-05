package com.ayoprez.openscores

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ayoprez.openscores.ui.MainActivity
import com.ayoprez.openscores.utils.TestFileReader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val mockServer = MockWebServer()

    @Before
    fun setup() {
        mockServer.start(8080)
//        IdlingRegistry.getInstance().register(
//            OkHttp3IdlingResource.create("okhttp", OkHttpProvider.getOkHttpClient()))
    }

    @After
    fun teardown() {
        mockServer.shutdown()
    }

    @Test
    fun test_football_button_is_displays() {
        val text = composeTestRule.activity.getString(R.string.football)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun test_basketball_button_is_displays() {
        val text = composeTestRule.activity.getString(R.string.basketball)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun test_basketball_button_click_open_basketball_scores() {
        val text = composeTestRule.activity.getString(R.string.basketball)
        composeTestRule.onNodeWithText(text).performClick()

        val backButtonText = composeTestRule.activity.getString(R.string.back)
        composeTestRule.onNodeWithText(backButtonText).assertExists()
        composeTestRule.onNodeWithText(text).assertExists()
    }

    @Test
    fun test_successful_basketball_response() {
        mockServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(TestFileReader().readStringFromFile("success_response.json"))
            }
        }
    }

}