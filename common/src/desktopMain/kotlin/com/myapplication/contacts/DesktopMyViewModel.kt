package com.myapplication.contacts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.myapplication.contacts.presentation.ContactsViewModel
import com.myapplication.contacts.presentation.ContactsViewModelImpl
@Composable
actual fun myViewModel(): ContactsViewModel {
    return remember { ContactsViewModelImpl() }
}