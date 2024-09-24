package com.example.module3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.module3.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Toast.makeText(activity, "F CreateView", Toast.LENGTH_SHORT).show()

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(activity, "F ViewCreated", Toast.LENGTH_SHORT).show()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Toast.makeText(activity, "F DestroyView", Toast.LENGTH_SHORT).show()

        _binding = null
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        Toast.makeText(activity, "F Attach", Toast.LENGTH_SHORT).show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(activity, "F Create", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(activity, "F Start", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(activity, "F Resume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(activity, "F Pause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(activity, "F Stop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(activity, "F Destroy", Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()

        Toast.makeText(activity, "F Detach", Toast.LENGTH_SHORT).show()
    }

}