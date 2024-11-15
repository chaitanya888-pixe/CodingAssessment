package com.sample.chaitanyasampleapp

import app.cash.turbine.test
import com.sample.chaitanyasampleapp.data.model.fakeData
import com.sample.chaitanyasampleapp.domain.repository.DataRepository
import com.sample.chaitanyasampleapp.domain.usecase.GetListUseCase
import com.sample.chaitanyasampleapp.presentation.viewmodel.MainViewModel
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private val testScope: TestScope = TestScope()

    private lateinit var viewModel: MainViewModel

    private val repository: DataRepository = mockk {
        every { getListData("us") } returns flow {
            emit(Result.success(fakeData))
        }
    }

    private fun initializeViewModel() {
        viewModel = MainViewModel(getListUseCase = GetListUseCase(repository))
    }

    private fun startTest(
        testBody: suspend TestScope.() -> Unit,
    ) {
        testScope.runTest(testBody = testBody)
    }

    private val testDispatcher = TestCoroutineDispatcher() // Create a test dispatcher

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        // Set the Main dispatcher to use the test dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Reset the Main dispatcher after the test
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines() // Cleanup test dispatcher
    }

    @Test
    fun `Get NewsArticles from api `() {
        startTest {
            initializeViewModel()
            viewModel.state.test {
                with(awaitItem()) {
                    articles.shouldBe(fakeData)
                    error.shouldBeNull()
                    isLoading.shouldBeFalse()
                }
            }
        }
    }
    @Test
    fun `Get NewsArticles returns an error from API`() {
        every { repository.getListData("us") } returns flow {
            emit(Result.failure(Exception("Network Error")))
        }
        startTest {
            initializeViewModel()
            viewModel.state.test {
                with(awaitItem()) {
                    articles.shouldBeEmpty()
                    error.shouldBe("Network Error")
                    isLoading.shouldBeFalse()
                }
            }
        }
    }

    @Test
    fun `Get NewsArticles returns empty data from API`() {
        every { repository.getListData("us") } returns flow {
            emit(Result.success(emptyList()))
        }
        startTest {
            initializeViewModel()
            viewModel.state.test {
                with(awaitItem()) {
                    articles.shouldBe(emptyList())
                    error.shouldBeNull()
                    isLoading.shouldBeFalse()
                }
            }
        }
    }
}