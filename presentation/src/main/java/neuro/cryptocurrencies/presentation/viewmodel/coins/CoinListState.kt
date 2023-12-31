package neuro.cryptocurrencies.presentation.viewmodel.coins

import kotlinx.collections.immutable.ImmutableList
import neuro.cryptocurrencies.presentation.model.CoinTickerModel
import neuro.cryptocurrencies.presentation.model.ErrorMessage

data class CoinListState(
	val coins: ImmutableList<CoinTickerModel>? = null,
	val isError: Boolean = false,
	val errorMessage: ErrorMessage = ErrorMessage.Empty,
	val isLoading: Boolean = false,
	val isRefreshing: Boolean = false
)
