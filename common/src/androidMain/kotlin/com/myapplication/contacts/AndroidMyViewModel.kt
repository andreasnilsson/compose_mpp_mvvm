package com.myapplication.contacts

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapplication.contacts.presentation.ContactsViewModel
import com.myapplication.contacts.presentation.ContactsViewModelImpl
import kotlinx.coroutines.CoroutineScope

@Composable
actual fun myViewModel(): ContactsViewModel {
    return viewModel<ContactsViewModelAndroid>()
}

class ContactsViewModelAndroid : ViewModel(), ContactsViewModel by ContactsViewModelImpl() {
    override val scope: CoroutineScope get() = viewModelScope
}