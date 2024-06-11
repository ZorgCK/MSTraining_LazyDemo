package one.microstream.db;

import org.eclipse.serializer.collections.lazy.LazyArrayList;

import one.microstream.domain.Person;


public class DataRoot
{
	private LazyArrayList<Person> persons = new LazyArrayList<Person>(100000);
	
	public LazyArrayList<Person> getPersons()
	{
		return persons;
	}

	public void setPersons(LazyArrayList<Person> persons)
	{
		this.persons = persons;
	}
}
