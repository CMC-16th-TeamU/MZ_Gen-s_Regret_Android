package com.cmc.regret_aos.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.cmc.regret_aos.api.ApiService
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class FeedViewModel @Inject constructor(
//    private val apiService: ApiService
) : ViewModel() {

    val feedData = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { FeedDataSource() }
//        pagingSourceFactory = { FeedDataSource(apiService) }
    ).flow.cachedIn(viewModelScope)
}
