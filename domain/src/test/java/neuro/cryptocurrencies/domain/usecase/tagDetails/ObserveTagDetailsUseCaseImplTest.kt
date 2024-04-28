package neuro.cryptocurrencies.domain.usecase.tagDetails

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.ObserveTagDetailsRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class ObserveTagDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val observeTagDetailsRepository = mock<ObserveTagDetailsRepository>()

		val observeTagDetailsUseCase = ObserveTagDetailsUseCaseImpl(observeTagDetailsRepository)
		val tagId = "segwit"

		whenever(observeTagDetailsRepository.observeTagDetails(tagId)).thenReturn(flow {
			emit(
				buildTagDetails()
			)
		})

		verifyNoInteractions(observeTagDetailsRepository)

		val tagDetails = observeTagDetailsUseCase.execute(tagId).first()

		verify(observeTagDetailsRepository, times(1)).observeTagDetails(tagId)

		assertEquals(buildTagDetails(), tagDetails)
	}

	private fun buildTagDetails() = TagDetails("1", "Segwit", "I'm the segwit")
}
