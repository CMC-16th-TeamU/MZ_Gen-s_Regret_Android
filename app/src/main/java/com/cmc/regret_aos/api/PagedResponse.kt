package com.cmc.regret_aos.api

data class PagedResponse<T>(
    val totalPage: Int,
    val totalElement: Int,
    val data: List<T>,
    val isLast: Boolean
)
