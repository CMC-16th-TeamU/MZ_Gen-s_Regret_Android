package com.cmc.regret_aos.login

import android.os.Bundle
import android.util.Log
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
import com.cmc.regret_aos.databinding.FragmentBirthBinding
import com.cmc.regret_aos.databinding.FragmentGenderBinding
import com.cmc.regret_aos.databinding.FragmentMajorBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MajorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MajorFragment : Fragment() {

    val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentMajorBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentMajorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val majorItems = listOf("학생", "프리랜서", "자영업", "직장인", "기타")
        val majorAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_item, majorItems)
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.majorSpinner.adapter = majorAdapter

        binding.majorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.setMajor(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        binding.nextButton.setOnClickListener {
            Log.d("testLog", "onClick ${viewModel.major.value}")
            if(viewModel.major.value != null) {
                findNavController().navigate(R.id.action_majorFragment_to_fieldFragment)
            }
        }
    }

}