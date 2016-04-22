package com.cisco.cmad;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cisco.cmad.model.Activity;
import com.cisco.cmad.model.User;
import com.cisco.cmad.repository.ActivityRepository;
import com.cisco.cmad.repository.ActivityRepositoryStub;

@Path("/activities")
public class ActivityResource {
	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Activity> getAllActivities(){
		return activityRepository.findAllActivity();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityID}")
	public Activity getActivity(@PathParam("activityID") String activityID){
		return activityRepository.findActivity(activityID);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityID}/user")
	public User getActivityUser(@PathParam("activityID") String activityID){
		return activityRepository.findActivity(activityID).getUser();
	}

}
