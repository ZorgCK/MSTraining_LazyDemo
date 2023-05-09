package one.microstream.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import one.microstream.db.DB;
import one.microstream.domain.Person;
import one.microstream.reference.Lazy;
import one.microstream.utils.MockupUtils;


@Controller("/persons")
public class PersonController
{
	@Get("/create")
	public HttpResponse<String> createPersons()
	{
		IntStream.range(1, 201).forEach(i ->
		{
			List<Person> persons = MockupUtils.loadMockupData();
			DB.root.getPersonContainer().addPersons(persons);
		});
		
		DB.root.getPersonContainer().store(DB.storageManager);
		
		return HttpResponse.ok("Persons successfully created!");
	}
	
	@Get("/lastname")
	public List<Person> getPersons(@Nullable @QueryValue String lastname)
	{
		Map<String,Lazy<List<Person>>> personsByLastname = DB.root.getPersonContainer().getPersonsByLastname();
		
		Lazy<List<Person>> lazy = personsByLastname.get(lastname.substring(0,2));
		
		if(lazy != null)
		{
			return lazy.get().parallelStream()
			.filter(p -> p.getLastname().equalsIgnoreCase(lastname))
			.collect(Collectors.toList());
		}
		return null;
	}
	
	@Get("/init")
	public String initMicroStream()
	{
		return DB.storageManager.toString();
	}
}
