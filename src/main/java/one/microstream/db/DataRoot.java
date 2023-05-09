package one.microstream.db;

import one.microstream.collections.lazy.LazyArrayList;
import one.microstream.collections.lazy.LazySegmentUnloader;
import one.microstream.domain.Person;


public class DataRoot
{
	private LazyArrayList<Person> persons = new LazyArrayList<Person>(1000, new LazySegmentUnloader.Timed(200));
	
	public LazyArrayList<Person> getPersons()
	{
		return persons;
	}

	public void setPersons(LazyArrayList<Person> persons)
	{
		this.persons = persons;
	}
}
