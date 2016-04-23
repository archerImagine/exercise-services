package com.cisco.cmad.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cisco.cmad.model.Activity;

public class ActivityClient {
	private String rootEnd = "http://localhost:8080/exercise-services/webapi/";
	
	private Client client;
	
	public ActivityClient(){
		client = ClientBuilder.newClient();
	}
	
	/*public Activity get(String id){
		System.out.println("ActivityClient.get()");
		WebTarget target = client.target(this.rootEnd);
		This returns a XML.
		Activity response = target.path("activities/" +id).request().get(Activity.class);
		
		This returns a JSON
		Activity response = target.path("activities/" +id).request(MediaType.APPLICATION_JSON).get(Activity.class);
		return response;		
	}*/
	public Activity get(String id){
		System.out.println("ActivityClient.get(): " +id);
		WebTarget target = client.target(this.rootEnd);
		Response response = target.path("activities/" +id).request().get(Response.class);
		
		System.out.println("ActivityClient.get() " +response.getStatus());
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": this is an error");
		}
		
		return response.readEntity(Activity.class);
		
	}
	
	
	public List<Activity>get(){
		System.out.println("ActivityClient.get(LIST)");
		WebTarget target = client.target(this.rootEnd);
		List<Activity> response = target.path("activities/").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>(){});
		
		return response;
		
	}

	public Activity create(Activity activity) {
		WebTarget target = client.target(this.rootEnd);
		Response response = target.path("activities/activity")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": this is an error");
		}
		return response.readEntity(Activity.class);
	}

	public Activity update(Activity activity) {
		WebTarget target = client.target(this.rootEnd);
		Response response = target.path("activities/" +activity.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": this is an error");
		}
		return response.readEntity(Activity.class);
	}

	public void delete(String activityID) {
		System.out.println("ActivityClient.delete()" +activityID);
		WebTarget target = client.target(this.rootEnd);
		Response response = target.path("activities/" +activityID)
				.request(MediaType.APPLICATION_JSON)
				.delete();
		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": this is an error");
		}
	}

}
