package neuro.cryptocurrencies.data.model.network.tag


import com.google.gson.annotations.SerializedName

data class TagDetailsDto(
	@SerializedName("description")
	val description: String,
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val name: String
)