package com.cisco.cmad;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	
	/*@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityID}")
	public Activity getActivity(@PathParam("activityID") String activityID){
		return activityRepository.findActivity(activityID);
	}*/
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityID}")
	public Response getActivity(@PathParam("activityID") String activityID){
		System.out.println("ActivityResource.getActivity() id: " +activityID);
		Response response = null;
		if (activityID == null || activityID.length() < 4) {
			System.out.println("ActivityResource.getActivity(): BAD Request");
			return Response.status(Status.BAD_REQUEST).build();
		}
		Activity activity = activityRepository.findActivity(activityID);
		if (activity == null) {
			System.out.println("ActivityResource.getActivity(): NOT FOUND");
			return Response.status(Status.NOT_FOUND).build();
		}else{
			System.out.println("ActivityResource.getActivity(): OK Request");
			return Response.ok().entity(activity).build();
		}
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityID}/user")
	public User getActivityUser(@PathParam("activityID") String activityID){
		return activityRepository.findActivity(activityID).getUser();
	}
	
	@PUT
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(Activity activity){
		System.out.println("ActivityResource.update() : " +activity.getDescription());
		System.out.println("ActivityResource.update() : " +activity.getDuration());
		System.out.println("ActivityResource.update() : " +activity.getId());
		
		activityRepository.update(activity);
		
		return Response.ok().entity(activity).build();
	}
	
	@DELETE
	@Path("{activityID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete(@PathParam("activityID") String activityID){
		System.out.println("ActivityResource.delete() : " +activityID);
		
		activityRepository.delete(activityID);
		return Response.ok().build();
		
	}
}
