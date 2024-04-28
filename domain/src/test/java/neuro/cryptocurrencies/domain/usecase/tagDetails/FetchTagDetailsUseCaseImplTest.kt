package neuro.cryptocurrencies.domain.usecase.tagDetails

import kotlinx.coroutines.test.runTest
import neuro.cryptocurrencies.domain.entity.TagDetails
import neuro.cryptocurrencies.domain.repository.tag.GetTagDetailsRepository
import neuro.cryptocurrencies.domain.repository.tag.SaveTagDetailsRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class FetchTagDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val getTagDetailsRepository = mock<GetTagDetailsRepository>()
		val saveTagDetailsRepository = mock<SaveTagDetailsRepository>()

		val fetchTagDetailsUseCase =
			FetchTagDetailsUseCaseImpl(getTagDetailsRepository, saveTagDetailsRepository)

		val tagId = "segwit"

		whenever(getTagDetailsRepository.getTagDetails(tagId)).thenReturn(
			buildTagDetails()
		)

		verifyNoInteractions(getTagDetailsRepository)
		verifyNoInteractions(saveTagDetailsRepository)

		fetchTagDetailsUseCase.execute(tagId)

		verify(getTagDetailsRepository, times(1)).getTagDetails(tagId)
		verify(saveTagDetailsRepository, times(1)).saveTagDetails(buildTagDetails())
	}

	private fun buildTagDetails() = TagDetails(
		"1",
		"Segwit",
		"Segwit is a description"
	)
}