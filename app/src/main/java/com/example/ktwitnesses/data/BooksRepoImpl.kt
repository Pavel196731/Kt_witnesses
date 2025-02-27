package com.example.ktwitnesses.data

import com.example.ktwitnesses.network.BookService

class BooksRepoImpl (
	private val bookService: BookService
) : BooksRepository {
	override suspend fun getBooks(
		query: String,
		maxResults: Int
	): List<Book> = bookService.bookSearch(query, maxResults).items.map { items ->
		Book(
			title = items.volumeInfo?.title,
			previewLink = items.volumeInfo?.previewLink,
			imageLink = items.volumeInfo?.imageLinks?.thumbnail
		)
	}
}