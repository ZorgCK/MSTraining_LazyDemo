package one.microstream.db;

import java.util.ArrayList;
import java.util.List;

import one.microstream.domain.Person;
import one.microstream.reference.Lazy;


public class DataRoot
{
	private Lazy<List<Person>> persons = Lazy.Reference(new ArrayList<Person>());

	public Lazy<List<Person>> getPersons()
	{
		return persons;
	}

	public void setPersons(Lazy<List<Person>> persons)
	{
		this.persons = persons;
	}
	
	
	
}
