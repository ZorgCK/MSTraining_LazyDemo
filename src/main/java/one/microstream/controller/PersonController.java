package one.microstream.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import one.microstream.db.DB;
import one.microstream.domain.Person;
import one.microstream.utils.MockupUtils;


@Controller("/persons")
public class PersonController
{
	@Get("/create")
	public HttpResponse<String> createPersons()
	{
		IntStream.range(1, 201).forEach(i -> {
			List<Person> persons = MockupUtils.loadMockupData();			
			DB.root.addPersons(persons);
		});
				
		DB.storageManager.store(DB.root.getPersons());

		System.out.println(DB.root.getPersons().size());
		
		return HttpResponse.ok("Persons successfully created!");
	}
		
	@Get
	public Set<String> getPersons()
	{
		return DB.root.getPersons().keySet();
	}
	
	@Get("/init")
	public String initMicroStream()
	{
		return DB.storageManager.toString();
	}
}
