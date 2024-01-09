package com.example.examensegunda

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.examensegunda.data.Client
import com.example.examensegunda.data.ClientDao
import kotlinx.coroutines.launch

class ExamViewModel(private val clientDao: ClientDao) : ViewModel() {

    var allClients: LiveData<List<Client>> = clientDao.getClients().asLiveData()

    private fun insertClient(client: Client) {
        viewModelScope.launch {
            clientDao.insert(client)
        }
    }

    private fun getNewClientEntry(
        clientName: String,
        clientAddress: String,
        clientPhone: String,
        clientEmail: String,
        clientPhoto: String
    ): Client {
        return Client(
            name = clientName,
            address = clientAddress,
            phone = clientPhone,
            email = clientEmail,
            imageUrl = clientPhoto
        )
    }

    private fun updateClient(client: Client) {
        viewModelScope.launch {
            clientDao.update(client)
        }
    }

    //To edit Client
    private fun getUpdatedClientEntry(
        clientId: Int,
        clientName: String,
        clientAddress: String,
        clientPhone: String,
        clientEmail: String,
        clientPhoto: String
    ): Client {
        return Client(
            id = clientId,
            name = clientName,
            address = clientAddress,
            phone = clientPhone,
            email = clientEmail,
            imageUrl = clientPhoto
        )
    }

    fun updateClient(
        clientId: Int,
        clientName: String,
        clientAddress: String,
        clientPhone: String,
        clientEmail: String,
        clientPhoto: String
    ) {
        val updateClient =
            getUpdatedClientEntry(clientId, clientName, clientAddress, clientPhone, clientEmail, clientPhoto)
        updateClient(updateClient)
    }

    fun addNewClient(
        clientName: String,
        clientAddress: String,
        clientPhone: String,
        clientEmail: String,
        clientPhoto: String
    ) {
        val newClient = getNewClientEntry(clientName, clientAddress, clientPhone, clientEmail, clientPhoto)
        insertClient(newClient)
    }

    fun isEntryValid(
        clientName: String,
        clientAddress: String,
        clientPhone: String,
        clientEmail: String,
        clientPhoto: String
    ): Boolean {
        if (clientName.isBlank() || clientAddress.isBlank() || clientPhone.isBlank() || clientEmail.isBlank() || clientPhoto.isBlank()) {
            return false
        }
        return true
    }

    fun retrieveClient(id: Int): LiveData<Client> {
        return clientDao.getClient(id).asLiveData()
    }

    fun deleteClient(client: Client) {
        viewModelScope.launch {
            clientDao.delete(client)
        }
    }


    /**
     * Factory class to instantiate the [ViewModel] instance.
     */

    class ExamViewModelFactory(private val clientDao: ClientDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExamViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ExamViewModel(clientDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }

}