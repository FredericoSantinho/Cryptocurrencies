package neuro.cryptocurrencies.presentation.ui.screens.coinDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.cryptocurrencies.presentation.model.TeamMemberModel
import neuro.cryptocurrencies.presentation.ui.theme.CryptocurrenciesTheme

@Composable
fun TeamMemberComposable(
	teamMemberModel: TeamMemberModel,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = teamMemberModel.name,
			style = MaterialTheme.typography.h6
		)
		Spacer(modifier = Modifier.height(4.dp))
		Text(
			text = teamMemberModel.position,
			style = MaterialTheme.typography.body2,
			fontStyle = FontStyle.Italic
		)
	}
	Divider(
		thickness = 1.dp,
		color = MaterialTheme.colors.onSurface,
		modifier = Modifier.padding(top = 8.dp)
	)
}

@Preview
@Composable
fun PreviewTeamListItem() {
	CryptocurrenciesTheme {
		TeamMemberComposable(TeamMemberModel("1", "Satoshi Nakamoto", "Founder"))
	}
}