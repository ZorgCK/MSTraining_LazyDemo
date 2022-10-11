package one.microstream.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import one.microstream.domain.Person;
import one.microstream.reference.Lazy;


public class DataRoot
{
	private Map<String, Lazy<Person>> persons = new HashMap<String, Lazy<Person>>();

	public Map<String, Lazy<Person>> getPersons()
	{
		return persons;
	}

	public void addPersons(List<Person> persons)
	{
		persons.stream().forEach(p -> this.persons.put(p.getUuid()+"_"+p.getLastname(), Lazy.Reference(p)));
	}

	
	
	
}
