package neuro.cryptocurrencies.data.model.network.tag


import com.google.gson.annotations.SerializedName

data class TagDto(
	@SerializedName("coin_counter")
	val coinCounter: Int,
	@SerializedName("description")
	val description: String,
	@SerializedName("ico_counter")
	val icoCounter: Int,
	@SerializedName("id")
	val id: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("type")
	val type: String
)