package pl.inpost.recruitmenttask

import com.techworkscc.domain.ListNewsVO
import com.techworkscc.domain.NewsUseCase
import com.techworkscc.domain.NewsVO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import net.bytebuddy.utility.RandomString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class NewsUseCaseTest {

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
    private lateinit var newsUseCase: NewsUseCase

    @Test
    fun `test getShipments is not empty`() {
        //Arrange
        runBlocking {
            given(newsUseCase.loadNews()).willReturn(randomListVO())
        }
        runBlocking {
            launch(Dispatchers.Main) {
                //Act
                val voList = newsUseCase.loadNews()

                //Assert
                assertEquals(voList.listNewsVO.isNotEmpty(), true)
                assertEquals(voList.listNewsVO.size, 5)
            }
        }
    }

    @Test
    fun `test getShipments is empty`() {
        //Arrange
        runBlocking {
            given(newsUseCase.loadNews()).willReturn(ListNewsVO(emptyList()))
        }
        runBlocking {
            launch(Dispatchers.Main) {
                //Act
                val voList = newsUseCase.loadNews()

                //Assert
                assertEquals(voList.listNewsVO.isEmpty(), true)
                assertEquals(voList.listNewsVO.size, 0)
            }
        }
    }

    private fun randomListVO(): ListNewsVO {
        return ListNewsVO(getVOItems())
    }

    private fun getVOItems(): MutableList<NewsVO> {
        val items = mutableListOf<NewsVO>()
        items.add(randomNewsVO())
        items.add(randomNewsVO())
        items.add(randomNewsVO())
        items.add(randomNewsVO())
        items.add(randomNewsVO())
        return items
    }


    private fun randomNewsVO(): NewsVO {
        return NewsVO(
            sourceName = RandomString.make(5),
            title = RandomString.make(5),
            publishedAt = LocalDateTime.now(),
            urlToImage = RandomString.make(5),
            description = RandomString.make(5),
            content = RandomString.make(50)
        )
    }


}