package one.microstream.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.serializer.persistence.types.Persister;
import org.eclipse.serializer.persistence.types.Storer;
import org.eclipse.serializer.reference.Lazy;


public class Persons
{
	private Map<String, Lazy<List<Person>>>	personsByLastname	= new HashMap<String, Lazy<List<Person>>>();
	private Map<String, Lazy<List<Person>>>	personsByMail		= new HashMap<String, Lazy<List<Person>>>();
	
	public void addPersons(List<Person> persons)
	{
		persons.stream().forEach(p ->
		{
			this.personsByLastname.computeIfAbsent(
				p.getLastname().substring(0, 2),
				k -> Lazy.Reference(new ArrayList<>())).get().add(p);
			this.personsByMail.computeIfAbsent(
				p.getMail().substring(0, 2),
				k -> Lazy.Reference(new ArrayList<>())).get().add(p);
		});
	}
	
	public void store(Persister persiter)
	{
		Storer es = persiter.createEagerStorer();
		es.store(personsByLastname);
		es.commit();
		
		persiter.storeAll(personsByMail);
		es.store(personsByMail);
		es.commit();
	}
	
	public Map<String, Lazy<List<Person>>> getPersonsByLastname()
	{
		return personsByLastname;
	}
	
	public void setPersonsByLastname(Map<String, Lazy<List<Person>>> personsByLastname)
	{
		this.personsByLastname = personsByLastname;
	}
	
	public Map<String, Lazy<List<Person>>> getPersonsByMail()
	{
		return personsByMail;
	}
	
	public void setPersonsByMail(Map<String, Lazy<List<Person>>> personsByMail)
	{
		this.personsByMail = personsByMail;
	}	
}
