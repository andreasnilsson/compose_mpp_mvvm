package com.myapplication.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapplication.contacts.presentation.ContactClickEvent
import com.myapplication.contacts.presentation.ContactsViewModel
import com.myapplication.contacts.ui.ContactContent

/**
 * Handles ViewModel binding. Pushes data down and delegates upstreamed view events.
 */
@Composable
fun ContactsScreen(model: ContactsViewModel = myViewModel()) {
    val allContacts by model.contactList.collectAsState()

    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp),
    ) {
        ContactContent(
            allContacts = allContacts,
            onContactClicked = { id ->
                model.uiEvents.tryEmit(ContactClickEvent(id))
            }
        )
    }
}

@Composable
expect fun myViewModel(): ContactsViewModel