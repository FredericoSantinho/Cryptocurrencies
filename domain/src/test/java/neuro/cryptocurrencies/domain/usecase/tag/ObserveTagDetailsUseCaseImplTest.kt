package neuro.cryptocurrencies.domain.usecase.tag

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.ObserveTagDetailsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ObserveTagDetailsUseCaseImplTest {
	@Test
	fun test() = runBlocking {
		val observeTagDetailsRepository = mock<ObserveTagDetailsRepository>()

		val observeTagDetailsUseCase = ObserveTagDetailsUseCaseImpl(observeTagDetailsRepository)
		val tagId = "segwit"

		whenever(observeTagDetailsRepository.observeTagDetails(tagId)).thenReturn(flow {
			emit(
				buildTagDetails()
			)
		})

		val tagDetails = observeTagDetailsUseCase.execute(tagId).first()
		assertEquals(buildTagDetails(), tagDetails)
	}

	private fun buildTagDetails() = TagDetails("1", "Segwit", "I'm the segwit")
}
