package com.cisco.cmad.repository;

import java.util.List;

import com.cisco.cmad.model.Activity;

public interface ActivityRepository {

	List<Activity> findAllActivity();

	Activity findActivity(String activityID);

	void create(Activity activity);

	Activity update(Activity activity);

}