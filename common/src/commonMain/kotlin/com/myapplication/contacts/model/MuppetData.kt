package com.myapplication.contacts.model

/**
 * Some mocked contacts from public source.
 */
object MuppetData {
    val Kermit = Contact(
        "1",
        "Kermit",
        "the",
        "Frog",
        "https://upload.wikimedia.org/wikipedia/en/6/62/Kermit_the_Frog.jpg"
    )

    val MissPiggy = Contact(
        "2",
        "Miss",
        "",
        "Piggy",
        "https://upload.wikimedia.org/wikipedia/en/2/22/MissPiggy.jpg"
    )

    val FozzieBear = Contact(
        "2",
        "Fozzie",
        "",
        "Bear",
        "https://upload.wikimedia.org/wikipedia/en/5/51/Fozzie_Bear.jpg"
    )

    val Muppets = listOf(Kermit, MissPiggy, FozzieBear)
}
