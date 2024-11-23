package com.cmc.regret_aos.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.cmc.regret_aos.LoginViewModel
import com.cmc.regret_aos.MainActivity
import com.cmc.regret_aos.R
import com.cmc.regret_aos.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var emailComplete = false
    var passwordComplete = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.idView.addTextChangedListener {
            val email = binding.idView.text.toString()
            val emailPattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
            emailComplete = emailPattern.matcher(email).matches()
        }

        binding.pwView.addTextChangedListener {
            val password = binding.pwView.text.toString()
            passwordComplete = password.isNotBlank()
        }

        binding.nextButton.setOnClickListener {

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    val userId = viewModel.getUserId()
                    val email = viewModel.getUserEmail()
                    viewModel.getUserId()
                    if(userId != 0L && email == binding.idView.text.toString()) {
                        startActivity(
                            Intent(activity, MainActivity::class.java)
                        )
                    } else {
                        if (emailComplete && passwordComplete) {
                            viewModel.setEmail(binding.idView.text.toString())
                            viewModel.setPassword(binding.pwView.text.toString())
                            findNavController().navigate(R.id.action_SeconFragment_to_genderFragment)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}