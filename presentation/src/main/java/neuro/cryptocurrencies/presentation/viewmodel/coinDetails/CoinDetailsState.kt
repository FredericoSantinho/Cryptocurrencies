package neuro.cryptocurrencies.presentation.viewmodel.coinDetails

import neuro.cryptocurrencies.presentation.model.CoinDetailsWithPriceModel
import neuro.cryptocurrencies.presentation.model.DialogText
import neuro.cryptocurrencies.presentation.model.ErrorMessage

data class CoinDetailsState(
	val coinDetailsWithPriceModel: CoinDetailsWithPriceModel? = null,
	val isError: Boolean = false,
	val errorMessage: ErrorMessage = ErrorMessage.Empty,
	val isLoading: Boolean = false,
	val isRefreshing: Boolean = false,
	val showDialog: Boolean = false,
	val dialogLoading: Boolean = false,
	val dialogTitle: String? = null,
	val dialogText: DialogText = DialogText.Empty
)