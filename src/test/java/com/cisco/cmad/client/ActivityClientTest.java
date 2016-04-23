package com.cisco.cmad.client;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

import com.cisco.cmad.model.Activity;

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

}
