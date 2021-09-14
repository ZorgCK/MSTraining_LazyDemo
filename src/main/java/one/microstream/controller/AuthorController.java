package one.microstream.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import one.microstream.DB;
import one.microstream.domain.Author;


@Controller("/authors")
public class AuthorController
{
	@Get("/create")
	public HttpResponse<String> createBooks()
	{
		Author author = new Author("Charlotte", "Link", "c.link@example.com");
		
		DB.root.getAuthors().add(author);
		DB.storageManager.store(DB.root.getAuthors());
		
		return HttpResponse.ok("Author successfully created!");
	}
	
	@Get("/loadImage")
	public HttpResponse<String> loadImage()
	{
		ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
		Optional<InputStream> isOptional = loader.getResourceAsStream("00019.MTS");
		
		Optional<Author> findFirst = DB.root.getAuthors().stream().findFirst();
		
		if(findFirst.isPresent())
		{
			try
			{
				Author author = findFirst.get();
				author.setImage(IOUtils.toByteArray(isOptional.get()));
				
				DB.storageManager.store(author);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return HttpResponse.ok("Image successfully loaded!");
	}
	
	@Get
	public List<Author> getBook()
	{
		return DB.root.getAuthors();
	}
}
