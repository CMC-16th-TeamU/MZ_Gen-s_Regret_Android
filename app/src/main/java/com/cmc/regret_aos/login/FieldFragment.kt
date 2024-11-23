package com.cmc.regret_aos.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cmc.regret_aos.LoginViewModel
import com.cmc.regret_aos.R
import com.cmc.regret_aos.databinding.FragmentFieldBinding
import com.cmc.regret_aos.databinding.FragmentMajorBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FieldFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FieldFragment : Fragment() {

    val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentFieldBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentFieldBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fieldItems = listOf("IT/통신", "서비스", "제조", "교육", "의료", "건설", "금융/보험", "공공행정/국방")
        val majorAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, fieldItems)
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fieldSpinner.adapter = majorAdapter

        binding.fieldSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.setField(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        binding.nextButton.setOnClickListener {
            if(viewModel.field.value != null) {
                findNavController().navigate(R.id.action_fieldFragment_to_completeFragment)
            }
        }
    }
}