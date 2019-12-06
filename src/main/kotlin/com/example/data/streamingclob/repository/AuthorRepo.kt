package com.example.data.streamingclob.repository

import com.example.data.streamingclob.modle.Author
import com.example.data.streamingclob.modle.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

//@Transactional(readOnly = true)
interface AuthorRepo: JpaRepository<Author, Long> {
    fun findByName(name: String): Author
}

interface BookRepo: JpaRepository<Book, Long>

