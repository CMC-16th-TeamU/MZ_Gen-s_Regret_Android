package com.cmc.regret_aos.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cmc.regret_aos.LoginViewModel
import com.cmc.regret_aos.R
import com.cmc.regret_aos.databinding.FragmentGenderBinding
import com.cmc.regret_aos.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class GenderFragment : Fragment() {

    val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentGenderBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mailView.setOnClickListener {
            binding.feMailView.backgroundTintList = resources.getColorStateList(R.color.gray_7)
            binding.mailView.backgroundTintList = resources.getColorStateList(R.color.regret_blue)
            viewModel.setGender("MALE")

        }
        binding.feMailView.setOnClickListener {
            binding.mailView.backgroundTintList = resources.getColorStateList(R.color.gray_7)
            binding.feMailView.backgroundTintList = resources.getColorStateList(R.color.regret_blue)
            viewModel.setGender("FEMALE")
        }

        binding.nextButton.setOnClickListener {
            if(viewModel.gender.value != null) {
                findNavController().navigate(R.id.action_genderFragment_to_birthFragment)
            }
        }
    }

}