package com.example.examensegunda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.examensegunda.data.Client
import com.example.examensegunda.databinding.FragmentClientDetailsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ClientDetailsFragment : Fragment() {

    private val navigationArgs: ClientDetailsFragmentArgs by navArgs()

    lateinit var client: Client

    private val viewModel: ExamViewModel by activityViewModels {
        ExamViewModel.ExamViewModelFactory((activity?.application as ExamApplication).database.clientDao())
    }

    private var _binding: FragmentClientDetailsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientDetailsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ ->
                Toast.makeText(this.context, "It's okay bro,chill", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteClient()
            }
            .show()
    }

    private fun deleteClient() {
        viewModel.deleteClient(client)
        findNavController().navigateUp()
    }

    private fun bind(client: Client) {
        binding?.apply {
            clientName.text = client.name
            clientAddress.text = client.address
            clientPhone.text = client.phone
            clientEmail.text = client.email
            Glide.with(clientPhoto)
                .load(client.imageUrl) // Debes asegurarte de que client.imageUrl contenga la URL de la imagen
                .into(binding!!.clientPhoto) // clientPhoto es la ImageView donde se mostrará la foto

            editClient.setOnClickListener { editClient() }
            deleteClient.setOnClickListener { showConfirmationDialog() }
        }
    }

    private fun editClient() {
        val action = ClientDetailsFragmentDirections.actionClientDetailsFragmentToAddClientFragment(
            getString(R.string.edit_fragment_title),
            client.id
        )
        this.findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.clientId
        viewModel.retrieveClient(id).observe(this.viewLifecycleOwner) { selectedClient ->
            client = selectedClient
            bind(client)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}