package one.microstream.db;

import one.microstream.domain.Persons;


public class DataRoot
{
	private Persons personContainer = new Persons();

	public Persons getPersonContainer()
	{
		return personContainer;
	}

	public void setPersonContainer(Persons personContainer)
	{
		this.personContainer = personContainer;
	}
	
	
	
}
