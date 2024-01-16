package one.microstream.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.serializer.reference.Lazy;

import one.microstream.domain.Person;


public class DataRoot
{
	private Map<String, Lazy<Person>> persons = new HashMap<String, Lazy<Person>>();

	public Map<String, Lazy<Person>> getPersons()
	{
		return persons;
	}

	public void addPersons(List<Person> persons)
	{
		persons.stream().forEach(p -> this.persons.put(p.getMail() + " " + UUID.randomUUID().toString(), Lazy.Reference(p)));
	}	
}
