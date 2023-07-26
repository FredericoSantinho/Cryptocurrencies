package com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import neuro.coin.paprika.presentation.model.TeamModel

@Composable
fun TeamListItem(
	teamModel: TeamModel,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = teamModel.name,
			style = MaterialTheme.typography.h6
		)
		Spacer(modifier = Modifier.height(4.dp))
		Text(
			text = teamModel.position,
			style = MaterialTheme.typography.body2,
			fontStyle = FontStyle.Italic
		)
	}
}