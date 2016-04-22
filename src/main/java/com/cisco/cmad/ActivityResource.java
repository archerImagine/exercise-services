package com.cisco.cmad;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.cisco.cmad.model.Activity;
import com.cisco.cmad.model.User;
import com.cisco.cmad.repository.ActivityRepository;
import com.cisco.cmad.repository.ActivityRepositoryStub;

@Path("/activities")
public class ActivityResource {
	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity create(Activity activity){
		System.out.println("ActivityResource.create() : " +activity.getDescription());
		System.out.println("ActivityResource.create() : " +activity.getDuration());
		System.out.println("ActivityResource.create() : " +activity.getId());
		
		activityRepository.create(activity);
		return activity;
	}
	
	@POST
	@Path("activity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity createActivityParams(MultivaluedMap<String, String> formParam){
		System.out.println("ActivityResource.createActivityParams()" +formParam.getFirst("desription"));
		System.out.println("ActivityResource.createActivityParams()" +formParam.getFirst("duration"));
		System.out.println("ActivityResource.createActivityParams()" +formParam.getFirst("id"));
		
		Activity activity = new Activity();
		activity.setDescription(formParam.getFirst("desription"));
		activity.setDuration(Integer.parseInt(formParam.getFirst("duration")));
		activity.setId(Integer.parseInt(formParam.getFirst("id")));
		
		activityRepository.create(activity);
		
		return activity;		
	}
	
	
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
