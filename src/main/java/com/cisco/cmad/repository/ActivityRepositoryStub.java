package com.cisco.cmad.repository;

import java.util.ArrayList;
import java.util.List;

import com.cisco.cmad.model.Activity;
import com.cisco.cmad.model.User;

public class ActivityRepositoryStub implements ActivityRepository {
	
	/* (non-Javadoc)
	 * @see com.cisco.cmad.repository.ActivityRepository#findAllActivity()
	 */
	@Override
	public List<Activity> findAllActivity(){
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		
		activity1.setDescription("Swiming");
		activity1.setDuration(55);		
		activities.add(activity1);
		
		Activity activity2 = new Activity();
		
		activity2.setDescription("Cycling");
		activity2.setDuration(120);
		activities.add(activity2);
		
		return activities;
	}

	@Override
	public Activity findActivity(String activityID) {
		if (activityID.equals("7777")) {
			return null;
		}
		
		Activity activity1 = new Activity();
		
		activity1.setDescription("Swiming");
		activity1.setDuration(55);		
		activity1.setId(1234);
		
		User user = new User();
		user.setId("5678");
		user.setName("Animesh");
		
		activity1.setUser(user);
		
		return activity1;
	}

	@Override
	public void create(Activity activity) {
		//Should Issue an Insert in data base.
		
		Activity myActivity = activity;
		
		System.out.println("ActivityRepositoryStub.create(): " +myActivity.getDescription());
		System.out.println("ActivityRepositoryStub.create(): " +myActivity.getDuration());
		System.out.println("ActivityRepositoryStub.create(): " +myActivity.getId());
		
	}

}
