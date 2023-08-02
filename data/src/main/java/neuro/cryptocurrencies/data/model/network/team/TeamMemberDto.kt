package neuro.cryptocurrencies.data.model.network.team


import com.google.gson.annotations.SerializedName

data class TeamMemberDto(
	@SerializedName("description")
	val description: String?,
	@SerializedName("id")
	val id: String,
	@SerializedName("links")
	val links: Links,
	@SerializedName("name")
	val name: String,
	@SerializedName("positions")
	val positions: List<Position>,
	@SerializedName("teams_count")
	val teamsCount: Int
)