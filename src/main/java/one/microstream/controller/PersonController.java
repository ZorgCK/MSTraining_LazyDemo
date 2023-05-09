package one.microstream.controller;

import java.util.List;
import java.util.stream.Collectors;
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
			DB.root.getPersons().addAll(persons);
		});
				
		DB.storageManager.store(DB.root.getPersons());
		
		return HttpResponse.ok("Persons successfully created! " + DB.root.getPersons().size() + " in total.");
	}
		
	@Get
	public List<String> getPersons()
	{
		return DB.root.getPersons().stream().map(p -> p.getLastname()).collect(Collectors.toList());
	}
}
