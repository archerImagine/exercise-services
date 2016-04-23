package com.cisco.cmad.client;

import static org.junit.Assert.*;

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

}
