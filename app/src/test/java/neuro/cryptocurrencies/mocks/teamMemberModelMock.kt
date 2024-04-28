package neuro.cryptocurrencies.mocks

import neuro.cryptocurrencies.presentation.model.TeamMemberModel

fun teamMemberModelMock() = TeamMemberModel("satoshi-nakamoto", "Satoshi Nakamoto", "Founder")

fun teamMemberModelMockList() = listOf(teamMemberModelMock())