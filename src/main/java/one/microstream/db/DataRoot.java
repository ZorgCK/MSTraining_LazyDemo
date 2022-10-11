package one.microstream.db;

import java.util.ArrayList;
import java.util.List;

import one.microstream.domain.Person;


public class DataRoot
{
	private List<Person> persons = new ArrayList<Person>();
	
	public List<Person> getPersons()
	{
		return persons;
	}
	
	public void setPersons(List<Person> persons)
	{
		this.persons = persons;
	}
	
}
