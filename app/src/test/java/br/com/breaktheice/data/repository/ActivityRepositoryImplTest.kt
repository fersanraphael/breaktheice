package br.com.breaktheice.data.repository

import br.com.breaktheice.data.mapper.ActivityMapper
import br.com.breaktheice.data.mapper.IMapper
import br.com.breaktheice.data.model.ActivityDTO
import br.com.breaktheice.data.source.IActivityDatabaseSource
import br.com.breaktheice.data.source.IActivityWeb
import br.com.breaktheice.domain.model.ActivityModel
import br.com.breaktheice.domain.utility.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ActivityRepositoryImplTest {

    private lateinit var activityDTO: ActivityDTO

    private lateinit var activityMapper: ActivityMapper

    private lateinit var activityRepositoryImpl: ActivityRepositoryImpl

    @Mock
    private lateinit var localActivityDataSource: IActivityDatabaseSource

    @Mock
    private lateinit var activityWeb: IActivityWeb

    @Mock
    private lateinit var mapperModel: IMapper<ActivityModel, ActivityDTO>

    @Mock
    private lateinit var mapperDTO: IMapper<ActivityDTO, ActivityModel>

    @Before
    fun setup() {
        activityDTO = ActivityDTO(
            _id = 0,
            favorite = false,
            participants = 0,
            activity = "activity",
            key = "key",
            link = "link",
            type = "type",
            accessibility = 0.0F,
            price = 0.0F,
        )
        activityMapper = ActivityMapper(
            activityModelToActivityDTOMapper = mapperModel,
            activityDTOToActivityModelMapper = mapperDTO
        )
        activityRepositoryImpl = ActivityRepositoryImpl(
            activityMapper = activityMapper,
            localActivityDataSource = localActivityDataSource,
            remoteActivityDataSource = activityWeb,
            coroutineDispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun `Assert that success`() {
        runBlocking {
            `when`(activityWeb.callActivity()).thenReturn(Response.success(activityDTO))

            val result: Result<ActivityModel> = activityRepositoryImpl.callActivity()
            assert(result is Result.Success)
        }
    }

    @Test
    fun `Assert that error`() {
        runBlocking {
            `when`(activityWeb.callActivity()).thenReturn(Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "{}")))

            val result: Result<ActivityModel> = activityRepositoryImpl.callActivity()
            assert(result is Result.Failure)
        }
    }
}
