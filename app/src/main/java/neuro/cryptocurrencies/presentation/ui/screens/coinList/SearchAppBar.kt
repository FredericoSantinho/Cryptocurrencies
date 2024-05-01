package neuro.cryptocurrencies.presentation.ui.screens.coinList

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.cryptocurrencies.R
import neuro.cryptocurrencies.presentation.ui.common.composables.TextFieldWithoutPadding
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme

@Composable
fun SearchAppBar(onSearchTerm: (String) -> Unit = {}) {
	var searching by rememberSaveable {
		mutableStateOf(false)
	}
	var searchTerm by rememberSaveable {
		mutableStateOf("")
	}
	val focusRequester = remember { FocusRequester() }

	TopAppBar(
		elevation = 4.dp,
		title = {
			if (!searching) Text(
				text = "Cryptocurrencies",
				color = MaterialTheme.colors.background
			) else {
				TextFieldWithoutPadding(
					searchTerm,
					{
						searchTerm = it
						onSearchTerm(it)
					},
					colors = TextFieldDefaults.textFieldColors(
						focusedIndicatorColor = Color.Black,
						cursorColor = Color.Black,
					),
					textStyle = MaterialTheme.typography.body2.copy(color = Color.Black),
					label = {
						Text(
							text = stringResource(id = R.string.search_for_a_coin),
							color = Color.DarkGray
						)
					},
					maxLines = 1,
					modifier = Modifier.focusRequester(focusRequester)
				)

				LaunchedEffect(key1 = Unit) {
					focusRequester.requestFocus()
				}
			}
		},
		backgroundColor = MaterialTheme.colors.primary,
		actions = {
			IconButton(onClick = {
				if (searching) {
					searchTerm = ""
					onSearchTerm(searchTerm)
				}
				searching = !searching
			}) {
				Icon(
					if (searching)
						Icons.Filled.Close
					else Icons.Filled.Search, null, tint = Color.Black
				)
			}
		})
}

@Preview
@Composable
fun PreviewSearchAppBar() {
	CryptocurrenciesTheme {
		SearchAppBar({})
	}
}