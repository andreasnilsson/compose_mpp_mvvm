package com.myapplication.contacts.model

import com.myapplication.contacts.model.MuppetData.Muppets
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Simple data provider of contacts
 */
class ContactsRepository {
    private val repoScope = MainScope() + Default
    private val api: Api = object : Api {}

    private val _allContacts = MutableStateFlow<List<Contact>>(emptyList())
    val allContacts: StateFlow<List<Contact>> = _allContacts.asStateFlow()
        .onStart {
            // First load from a local cache
            withContext(IO) {
                _allContacts.value = loadFromLocalDb()
            }

            // Follow up with a refresh from external data source
            withContext(Default) {
                _allContacts.value = loadContactsFromNetwork()
            }
        }
        .stateIn(
            repoScope,
            SharingStarted.Lazily,
            emptyList()
        )

    private fun loadFromLocalDb(): List<Contact> {
        return emptyList()
    }

    private suspend fun loadContactsFromNetwork(): List<Contact> = withContext(Default) {
        createFakeData()
    }

    fun favoriteContact(id: String) {
        api.addToFavorites(id)
    }
}

interface Api {
    fun addToFavorites(contactId: String) {
        // TODO
    }
}

// Fake data

private fun createFakeData(): List<Contact> = (0..10).map(::newRandomMuppet)

private fun newRandomMuppet(id: Int): Contact {
    return Muppets[Random.nextInt(Muppets.size)].copy(id = id.toString())
}