package com.cmc.regret_aos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RetrospectFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()

    private lateinit var feedRecyclerView: RecyclerView
    private lateinit var feedAdapter: FeedAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retrospect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedRecyclerView = view.findViewById(R.id.feedRecyclerView)
        feedRecyclerView.layoutManager = LinearLayoutManager(context)

        feedAdapter = FeedAdapter()
        feedRecyclerView.adapter = feedAdapter

        val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayoutManager.VERTICAL
        )
        feedRecyclerView.addItemDecoration(dividerItemDecoration)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.feedData.collectLatest {
                    feedAdapter.submitData(it)
                }
            }
        }

        view.findViewById<Button>(R.id.testBtn1).setOnClickListener {
            Log.d("testLog", "Button Click 1")
            Log.d("testLog", "Data ${feedAdapter.snapshot().items.size}")
            Log.d("testLog", "Data ${feedAdapter.snapshot().items}")
            Log.d("testLog", "Button Click 2")
        }
    }

}