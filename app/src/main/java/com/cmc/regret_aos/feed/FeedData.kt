package com.cmc.regret_aos.feed

import com.google.gson.annotations.SerializedName
import java.util.Date

data class FeedData(
    @SerializedName("regretContent") var content: String = "",
)
