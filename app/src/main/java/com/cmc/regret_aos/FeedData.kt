package com.cmc.regret_aos

import com.google.gson.annotations.SerializedName
import java.util.Date

data class FeedData(
    @SerializedName("content") var content: String = "",
)
