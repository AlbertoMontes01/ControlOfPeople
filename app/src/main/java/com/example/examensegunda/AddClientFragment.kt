package com.example.examensegunda
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.examensegunda.data.Client
import com.example.examensegunda.databinding.FragmentAddClientBinding

class AddClientFragment : Fragment() {

    private val navigationArgs: ClientDetailsFragmentArgs by navArgs()

    private val viewModel: ExamViewModel by activityViewModels {
        ExamViewModel.ExamViewModelFactory(
            (activity?.application as ExamApplication).database
                .clientDao()
        )
    }



    private lateinit var client: Client

    private var _binding: FragmentAddClientBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddClientBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.clientName.text.toString(),
            binding.clientAddress.text.toString(),
            binding.clientPhone.text.toString(),
            binding.clientEmail.text.toString(),
            binding.clientPhoto.text.toString()
        )
    }

    private fun addNewClient() {
        if (isEntryValid()) {
            viewModel.addNewClient(
                binding.clientName.text.toString(),
                binding.clientAddress.text.toString(),
                binding.clientPhone.text.toString(),
                binding.clientEmail.text.toString(),
                binding.clientPhoto.text.toString()
            )
            val action = AddClientFragmentDirections.actionAddClientFragmentToClientListFragment()
            findNavController().navigate(action)

        }
    }

    private fun bind(client: Client) {

        binding.apply {
            clientName.setText(client.name, TextView.BufferType.SPANNABLE)
            clientAddress.setText(client.address, TextView.BufferType.SPANNABLE)
            clientPhone.setText(client.phone, TextView.BufferType.SPANNABLE)
            clientEmail.setText(client.email, TextView.BufferType.SPANNABLE)
            clientPhoto.setText(client.imageUrl, TextView.BufferType.SPANNABLE)
            saveAction.setOnClickListener { updateClient() }
        }
        /*

        if (client.toString().isNotEmpty()) {
            binding.apply {
                clientName.setText(client.name, TextView.BufferType.SPANNABLE)
                clientAddress.setText(client.address, TextView.BufferType.SPANNABLE)
                clientPhone.setText(client.phone, TextView.BufferType.SPANNABLE)
                clientEmail.setText(client.email, TextView.BufferType.SPANNABLE)
                saveAction.setOnClickListener { updateClient() }
            }
        } else{
            binding.apply {
                clientName.setText("")
                clientAddress.setText("")
                clientPhone.setText("")
                clientEmail.setText("")
                saveAction.setOnClickListener { updateClient() }
            }
        }
         */
    }

    private fun updateClient() {
        if (isEntryValid()) {
            viewModel.updateClient(
                this.navigationArgs.clientId,
                this.binding.clientName.text.toString(),
                this.binding.clientAddress.text.toString(),
                this.binding.clientPhone.text.toString(),
                this.binding.clientEmail.text.toString(),
                this.binding.clientPhoto.text.toString()
            )
            val action = AddClientFragmentDirections.actionAddClientFragmentToClientListFragment()

            this.findNavController().navigate(action)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.clientId
        println(id)
        if (id > 0) {
            viewModel.retrieveClient(id).observe(this.viewLifecycleOwner) {
                    selectedItem ->
                client = selectedItem
                bind(client)
            }
        } else{
            binding.saveAction.setOnClickListener {
                addNewClient()
            }
        }

        //binding.saveAction.setOnClickListener{
        //  addNewItem()
        //}
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }

}