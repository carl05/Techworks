package pl.inpost.recruitmenttask

import com.techworkscc.domain.NewsVO
import com.techworkscc.presentation.NewsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Mock
    private lateinit var newsViewModel: NewsViewModel

    @Test
    fun `test refresh data return not empty list`() {
        //Arrange
        runBlocking {
            val list = mutableListOf<NewsVO>()
            list.add(mock())
            given(newsViewModel.newsLivewData).willReturn(mock())
            given(newsViewModel.newsLivewData.value).willReturn(mock())
            given(newsViewModel.newsLivewData.value?.listNewsVO).willReturn(list)
        }
        //Act
        runBlocking {
            launch(Dispatchers.Main) {
                newsViewModel.loadNews()
            }
        }
        //Assert
        assertEquals(newsViewModel.newsLivewData.value?.listNewsVO?.isNotEmpty(), true)
    }

    @Test
    fun `test refresh data return not list`() {
        //Arrange
        runBlocking {
            given(newsViewModel.newsLivewData).willReturn(mock())
            given(newsViewModel.newsLivewData.value).willReturn(mock())
            given(newsViewModel.newsLivewData.value?.listNewsVO).willReturn(mutableListOf())
        }
        //Act
        runBlocking {
            launch(Dispatchers.Main) {
                newsViewModel.loadNews()
            }
        }
        //Assert
        assertEquals(newsViewModel.newsLivewData.value?.listNewsVO?.isEmpty(), true)
    }

}