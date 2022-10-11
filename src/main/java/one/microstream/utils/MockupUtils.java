package one.microstream.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import one.microstream.domain.Person;


public class MockupUtils
{
	@SuppressWarnings("unchecked")
	public static List<Person> loadMockupData()
	{
		
		List<Person> persons = new ArrayList<Person>();
		
		ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
		Optional<URL> booksURL = loader.getResource("mockup/persons.json");
		
		JSONParser personParser = new JSONParser();
		
		try
		{
			FileReader personReader = new FileReader(new File(booksURL.get().getFile()));
			// Read JSON file
			Object personJSON = personParser.parse(personReader);
			JSONArray personList = (JSONArray)personJSON;
			
			persons =
				(List<Person>)personList.parallelStream().map(o -> parsePerson((JSONObject)o)).collect(
					Collectors.toList());
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		return persons;
	}
	
	
	private static Person parsePerson(JSONObject p)
	{
		Person a = new Person();
		
		a.setLastname((String)p.get("last_name"));
		a.setFirstname((String)p.get("first_name"));
		a.setMail((String)p.get("email"));
		a.setGender((String)p.get("gender"));
		a.setIp((String)p.get("ip"));
		a.setValue1((String)p.get("value1"));
		a.setValue2((String)p.get("value2"));

		return a;
	}
}
