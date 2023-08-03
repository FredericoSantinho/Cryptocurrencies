package neuro.cryptocurrencies.domain.mocks

import neuro.cryptocurrencies.domain.entity.TeamMember

fun teamMemberMock() = TeamMember("satoshi-nakamoto", "Satoshi Nakamoto", "Founder")

fun teamMemberMockList() = listOf(teamMemberMock())