package com.cmc.regret_aos.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cmc.regret_aos.LoginViewModel
import com.cmc.regret_aos.R
import com.cmc.regret_aos.databinding.FragmentBirthBinding
import com.cmc.regret_aos.databinding.FragmentGenderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BirthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BirthFragment : Fragment() {

    private var _binding: FragmentBirthBinding? = null
    val binding get() = _binding!!

    val viewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentBirthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            binding.birthEditText.text.toString().let {
                if(it.length == 4) {
                    viewModel.setBirth(it)
                    findNavController().navigate(R.id.action_birthFragment_to_majorFragment)
                }
            }
        }
    }
}