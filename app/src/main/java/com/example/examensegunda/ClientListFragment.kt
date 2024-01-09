package com.example.examensegunda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examensegunda.data.ClientDao
import com.example.examensegunda.databinding.FragmentClientListBinding
import com.google.android.material.snackbar.Snackbar

class ClientListFragment : Fragment() {

    private val viewModel: ExamViewModel by activityViewModels {
        ExamViewModel.ExamViewModelFactory(
            (activity?.application as ExamApplication).database.clientDao()
        )
    }

    private lateinit var clientDao: ClientDao

    private var _binding: FragmentClientListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ClientListAdapter {
            val action =
                ClientListFragmentDirections.actionClientListFragmentFragmentToClientDetailsFragment(
                    it.id
                )
            this.findNavController().navigate(action)
        }

        binding.recylcerView.adapter = adapter

        viewModel.allClients.observe(this.viewLifecycleOwner) { clients ->
            if(clients.isEmpty()) {
                Snackbar.make(binding.root, "Not users found", Snackbar.LENGTH_SHORT ).show()
                //Toast.makeText(this.context, "Not users found", Toast.LENGTH_SHORT).show()
                clients.let { adapter.submitList(it) }
            }
                clients.let {
                    adapter.submitList(it)
                }
        }

        binding.recylcerView.layoutManager = LinearLayoutManager(this.context)
        binding.floattingActionButton.setOnClickListener {
            val action =
                ClientListFragmentDirections.actionClientListFragmentFragmentToAddClientFragment(
                    getString(R.string.add_fragment_title)
                )
            this.findNavController().navigate(action)
        }

    }


}