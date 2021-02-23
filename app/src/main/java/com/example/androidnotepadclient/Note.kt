package com.example.androidnotepadclient

class Note(
    var title: String,
    var description: String,
    var viewCount: Int
) {

    var id: Int = 0

    constructor(
        id: Int,
        title: String,
        description: String,
        viewCount: Int
    ) : this(title, description, viewCount) {
        this.id = id
    }
}