package neuro.coin.paprika.data.model.network.coin.details


import com.google.gson.annotations.SerializedName

data class Stats(
	@SerializedName("contributors")
	val contributors: Int,
	@SerializedName("followers")
	val followers: Int,
	@SerializedName("stars")
	val stars: Int,
	@SerializedName("subscribers")
	val subscribers: Int
)