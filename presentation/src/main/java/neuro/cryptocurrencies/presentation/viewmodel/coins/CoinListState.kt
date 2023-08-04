package neuro.cryptocurrencies.presentation.viewmodel.coins

import kotlinx.collections.immutable.ImmutableList
import neuro.cryptocurrencies.presentation.model.CoinTickerModel

data class CoinListState(
	val coins: ImmutableList<CoinTickerModel>? = null,
	val isError: Boolean = false,
	val errorMessage: String = "",
	val isLoading: Boolean = false,
	val isRefreshing: Boolean = false
)
