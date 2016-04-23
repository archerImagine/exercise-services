package com.cisco.cmad.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.cisco.cmad.model.Activity;
import com.cisco.cmad.model.ActivitySearch;

public class ActivitySearchClient {
	private String rootEnd = "http://localhost:8080/exercise-services/webapi/";
	private String search = "search/activities";
	private Client client;
	
	public ActivitySearchClient(){
		client = ClientBuilder.newClient();
	}
	
	public List<Activity> search (String param, List<String> searchValues){
		URI uri = UriBuilder.fromUri(this.rootEnd)
				.path(this.search)
				.queryParam(param, searchValues)
				.build();
		// http://localhost:8080/exercise-services/webapi/search/activities?description=Swimming&description=running
		System.out.println("ActivitySearchClient.search(): URI: " +uri.toString());
		
		WebTarget target = client.target(uri);
		
		List<Activity> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>> () {});
		System.out.println("ActivitySearchClient.search() response: " +response);
		return response;
		
	}

	public List<Activity> search(String param, List<String> searchValues, String secondParam, int durationFrom,
			String thirdParam, int durationTo) {
		URI uri = UriBuilder.fromUri(this.rootEnd)
				.path(this.search)
				.queryParam(param, searchValues)
				.queryParam(secondParam, durationFrom)
				.queryParam(thirdParam, durationTo)
				.build();
		// http://localhost:8080/exercise-services/webapi/search/activities?description=Swimming&description=running&durationFrom=30&durationTo=55
		System.out.println("ActivitySearchClient.search(): URI: " +uri.toString());
		
		WebTarget target = client.target(uri);
		
		List<Activity> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>> () {});
		System.out.println("ActivitySearchClient.search() response: " +response);
		return response;
	}

	public List<Activity> search(ActivitySearch search) {
		URI uri = UriBuilder.fromUri(this.rootEnd)
				.path(this.search)
				.build();
		System.out.println("ActivitySearchClient.search(): URI: " +uri.toString());
		
		WebTarget target = client.target(uri);
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(search, MediaType.APPLICATION_JSON));
//		System.out.println("ActivitySearchClient.search() response: " +response);
		return response.readEntity(new GenericType<List<Activity>> (){});
	}

}
