package one.microstream;

import java.util.ArrayList;
import java.util.List;

import one.microstream.domain.Author;
import one.microstream.domain.Book;


public class DataRoot
{
	private List<Author>	authors	= new ArrayList<Author>();
	private List<Book>		books	= new ArrayList<Book>();
	
	public List<Author> getAuthors()
	{
		return authors;
	}
	
	public void setAuthors(List<Author> authors)
	{
		this.authors = authors;
	}
	
	public List<Book> getBooks()
	{
		return books;
	}
	
	public void setBooks(List<Book> books)
	{
		this.books = books;
	}
	
}
