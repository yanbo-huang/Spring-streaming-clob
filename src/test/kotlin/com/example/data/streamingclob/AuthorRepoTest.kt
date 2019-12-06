package com.example.data.streamingclob

import com.example.data.streamingclob.modle.Author
import com.example.data.streamingclob.modle.Book
import com.example.data.streamingclob.repository.AuthorRepo
import com.example.data.streamingclob.repository.BookRepo
import com.example.data.streamingclob.utils.LobHelper.getClob
import org.hibernate.engine.jdbc.ClobProxy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.ResourceUtils
import java.io.BufferedReader
import java.io.StringReader

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class AuthorRepoTest {
    @Autowired
    private lateinit var authorRepo: AuthorRepo

    @Autowired
    private lateinit var bookRepo: BookRepo

    @Test
    fun `test save and write data to repository`() {
        val author1 = Author(name = "author1", books = listOf(ClobProxy.generateProxy(StringReader("b1"), 2)).map { Book(bookDetails = it) })
        val author2 = Author(name = "author2", books = listOf(ClobProxy.generateProxy(StringReader("b2"), 2)).map { Book(bookDetails = it) })
        val author3 = Author(name = "author3", books = listOf(ClobProxy.generateProxy(StringReader("b3"), 2)).map { Book(bookDetails = it) })

        authorRepo.save(author1)
        authorRepo.save(author2)
        authorRepo.save(author3)

        val fetchedAuthor1 = authorRepo.findByIdOrNull(1)!!

        val contentFile = ResourceUtils.getFile("classpath:content.csv")

        val contentReader = contentFile.inputStream().reader()

       val author4 =  Author(name = "author4", books = listOf(Book(bookDetails = getClob(contentReader, contentFile.length()))))
        authorRepo.save(author4)


        val fetchedAuthor = authorRepo.findByName("author4")

        val returnedAuthor = fetchedAuthor.dropBooks()

        authorRepo.deleteById(7)







//        fetchedBook4.let {
//            val bookContent = it.books[0]
//            val reader = BufferedReader(bookContent.bookDetails.characterStream)
//            var count = 0
//            reader.forEachLine {
//                if (count > 2000) return@forEachLine
//                println(it)
//                count++
//            }
//        }



    }

}