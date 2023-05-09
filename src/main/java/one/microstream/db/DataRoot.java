package one.microstream.db;

import one.microstream.collections.lazy.LazyArrayList;
import one.microstream.domain.Person;


public class DataRoot
{
	private LazyArrayList<Person> persons = new LazyArrayList<Person>();

	public LazyArrayList<Person> getPersons()
	{
		return persons;
	}

	public void setPersons(LazyArrayList<Person> persons)
	{
		this.persons = persons;
	}
	
	
	
}
