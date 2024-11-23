package com.cmc.regret_aos.feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmc.regret_aos.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        val birthSpinner = view.findViewById<Spinner>(R.id.birthSpinner)
        val genderSpinner = view.findViewById<Spinner>(R.id.genderSpinner)
        val majorSpinner = view.findViewById<Spinner>(R.id.majorSpinner)
        val fieldSpinner = view.findViewById<Spinner>(R.id.fieldSpinner)
        val sortSpinner = view.findViewById<Spinner>(R.id.sortSpinner)

        val birthItems = listOf("1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999")
        val genderItems = listOf("남성", "여성")
        val majorItems = listOf("학생", "프리랜서", "자영업", "직장인", "기타")
        val fieldItems = listOf("IT/통신", "서비스", "제조", "교육", "의료", "건설", "금융/보험", "공공행정/국방")
        val sortItems = listOf("오름차순", "내림차순")

        val birthAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, birthItems)
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, genderItems)
        val majorAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, majorItems)
        val fieldAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, fieldItems)
        val sortAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, sortItems)

        birthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        birthSpinner.adapter = birthAdapter
        genderSpinner.adapter = genderAdapter
        majorSpinner.adapter = majorAdapter
        fieldSpinner.adapter = fieldAdapter
        sortSpinner.adapter = sortAdapter

        birthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.saveBirth(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.saveGender(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        majorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.saveMajor(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        fieldSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.saveField(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.saveSort(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

}