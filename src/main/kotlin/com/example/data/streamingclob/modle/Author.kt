package com.example.data.streamingclob.modle

import java.sql.Clob
import javax.persistence.*

@Entity
data class Author(
    @Id
    @GeneratedValue
    var id: Long = -1,
    var name: String,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], orphanRemoval = true)
    var books: List<Book> = mutableListOf()
) {
    init {
        books.forEach { it.author = this}
    }

    fun dropBooks(): Author {
        this.books = emptyList(); return this
    }
}

@Entity
data class Book(
    @Id
    @GeneratedValue
    var id: Long = -1,
    @Lob
    var bookDetails: Clob,

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    var author: Author? = null
)
