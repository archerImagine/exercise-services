package com.cisco.cmad.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.cisco.cmad.model.Activity;

public class ActivityClient {
	private String rootEnd = "http://localhost:8080/exercise-services/webapi/";
	
	private Client client;
	
	public ActivityClient(){
		client = ClientBuilder.newClient();
	}
	
	public Activity get(String id){
		System.out.println("ActivityClient.get()");
		WebTarget target = client.target(this.rootEnd);
		
		
		Activity response = target.path("activities/" +id).request().get(Activity.class);
		return response;		
	}

}
