package com.myapplication.contacts.presentation

import com.myapplication.contacts.model.Contact
import com.myapplication.contacts.model.ContactsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Common ViewModel interface for all platforms.
 */
interface ContactsViewModel {
    val scope: CoroutineScope
    val contactList: StateFlow<List<ContactUiModel>>
    val uiEvents: MutableSharedFlow<UiEvent>
}

/**
 * Common ViewModel implementation for all platforms
 */
class ContactsViewModelImpl : ContactsViewModel {
    private val contactsRepo by lazy(NONE) { ContactsRepository() }

    override val scope: CoroutineScope = MainScope()
    override val contactList: StateFlow<List<ContactUiModel>> = contactsRepo
        .allContacts
        .map { it.map(::toUiModel) }
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    override val uiEvents = MutableSharedFlow<UiEvent>(1)

    init {
        // fetch and map from repo
        scope.launch {
            uiEvents.collect { e ->
                // map what to do with a UI event here e.g. talk to repo, update presentation state etc.
                when (e) {
                    // This could also be modeled as a flow if we want to.
                    is ContactClickEvent -> contactsRepo.favoriteContact(e.repoId())
                }

            }
        }
    }
}

/**
 * Normally id's don't need to be remapped, but sometimes you do want to.
 * In this example the type changes.
 */
private fun ContactClickEvent.repoId(): String = id.toString()

private fun toUiModel(contact: Contact): ContactUiModel = contact.run {
    ContactUiModel(
        id = id.hashCode().toLong(),
        name = listOf(firstName, middleName, lastName).joinToString(separator = " "),
        photoUrl = imageUrl,
    )
}

/**
 * A presentation interface for a contact. Note that this should only contain data that is actually
 * rendered, either explicitly or implicitly. The one exception is here is the [id].
 * While this might start off looking like a copy of the Model object it will diverge over time,
 * and while doing so provide its value by decoupling the two domains.
 */
class ContactUiModel(
    val id: Long,
    val name: String,
    val photoUrl: String,
)

/**
 * A generic interface for events coming from the ui/views.
 */
sealed interface UiEvent
class ContactClickEvent(val id: Long) : UiEvent