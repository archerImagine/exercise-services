package com.cisco.cmad.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.cisco.cmad.model.Activity;
import com.cisco.cmad.model.ActivitySearch;

public class ActivityClientTest {

	@Test
	public void testGet() {
		ActivityClient client = new ActivityClient();
		Activity activity = client.get("1234");
		System.out.println("ActivityClientTest.testGet()" +activity);
		
		assertNotNull(activity);
	}
	
	@Test
	public void testGetList(){
		ActivityClient client = new ActivityClient();
		List<Activity> activities = client.get();
		
		assertNotNull(activities);
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetWithBadRequest(){
		System.out.println("ActivityClientTest.testGetWithBadRequest()");
		ActivityClient client = new ActivityClient();
		client.get("12");
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetWithNotFound(){
		System.out.println("ActivityClientTest.testGetWithNotFound()");
		ActivityClient client = new ActivityClient();
		client.get("7777");
	}
	
	@Test
	public void testCreate(){
		ActivityClient client = new ActivityClient();
		Activity activity = new Activity();
		activity.setDescription("Swim");
		activity.setDuration(90);
		
		activity = client.create(activity);
		assertNotNull(activity);
		
	}
	
	@Test
	public void testPut(){
		Activity activity = new Activity();
		activity.setId(3456);
		activity.setDescription("Yoga");
		activity.setDuration(90);
		
		ActivityClient client = new ActivityClient();
		activity = client.update(activity);
		
		assertNotNull(activity);
	}
	
	@Test
	public void testDelete(){
		ActivityClient client = new ActivityClient();
		client.delete("1234");
	}
	
	@Test
	public void testSearch(){
		ActivitySearchClient client = new ActivitySearchClient();
		String param = "description";
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("Swimming");
		searchValues.add("running");
		
		List<Activity> activities = client.search(param, searchValues);
		System.out.println("ActivityClientTest.testSearch(): activities: " + activities);
		
		assertNotNull(activities);
	}
	@Test
	public void testSearchRange(){
		ActivitySearchClient client = new ActivitySearchClient();
		String param = "description";
		List<String> searchValues = new ArrayList<String>();
		searchValues.add("Swimming");
		searchValues.add("running");
		
		String secondParam = "durationFrom";
		int durationFrom = 30;
		
		String thirdParam = "durationTo";
		int durationTo = 55;
		
		List<Activity> activities = client.search(param, searchValues, secondParam, durationFrom, thirdParam, durationTo);
		System.out.println("ActivityClientTest.testSearch(): activities: " + activities);
		
		assertNotNull(activities);
	}
	
	@Test
	public void testSearchObject(){
		ActivitySearchClient client = new ActivitySearchClient();
		List<String> searchValues = new ArrayList<String>();
		
		searchValues.add("Swimming");
		searchValues.add("running");
		
		ActivitySearch search = new ActivitySearch();
		search.setDescription(searchValues);
		search.setDurationFrom(30);
		search.setDurationTo(55);
		
		List<Activity> activities = client.search(search);
		
		System.out.println("ActivityClientTest.testSearchObject()" +activities);
		assertNotNull(activities);
		
	}

}
