package com.myapplication.contacts.model

/**
 * Simple data class representing a contact
 */
data class Contact(
    val id: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val imageUrl: String,
)
