package neuro.cryptocurrencies.data.model.network.team


import com.google.gson.annotations.SerializedName

data class TeamMemberDto(
	@SerializedName("description")
	val description: String?,
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val name: String
)