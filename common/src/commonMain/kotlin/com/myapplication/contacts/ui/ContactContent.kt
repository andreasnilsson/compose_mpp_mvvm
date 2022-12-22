package com.myapplication.contacts.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.myapplication.contacts.presentation.ContactUiModel
import com.seiko.imageloader.rememberAsyncImagePainter

/**
 * Top level view for a contacts container.
 * This component only knows about ui models and how to delegate them.
 * It's very important that we never pass the ViewModel into a content screen or either of its children.
 * As doing so will couple the View with the ViewModel.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactContent(allContacts: List<ContactUiModel>, onContactClicked: (id: Long) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            stickyHeader { StickyHeader() }

            items(allContacts, key = { it.id }) {
                ContactListItem(it.photoUrl, it.name, onClick = { onContactClicked(it.id) })
            }
        }
    }
}

@Composable
fun StickyHeader() {
    Box(
        modifier = Modifier
            .background(colors.background)
            .fillMaxWidth()
    ) {
        Text(
            "All Contacts", Modifier
                .align(alignment = Alignment.Center)
                .padding(16.dp),
            style = typography.body1
        )
    }

}

/**
 * A simple view/component, which only requires teh data it actually needs to draw.
 * This makes this component very flexible and easy to replace.
 */
@Composable
fun ContactListItem(photoUrl: String, name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .background(colors.surface)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(
            rememberAsyncImagePainter(photoUrl),
            "Photo of $name",
            Modifier
                .clip(CircleShape)
                .size(96.dp)
        )
        Text(name, color = colors.onSurface, style = typography.body1)
    }
}
