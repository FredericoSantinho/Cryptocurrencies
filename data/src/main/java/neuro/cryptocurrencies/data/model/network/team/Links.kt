package neuro.cryptocurrencies.data.model.network.team


import com.google.gson.annotations.SerializedName

data class Links(
	@SerializedName("github")
	val github: List<Github>,
	@SerializedName("linkedin")
	val linkedin: List<Linkedin>,
	@SerializedName("medium")
	val medium: List<Medium>,
	@SerializedName("twitter")
	val twitter: List<Twitter>
)