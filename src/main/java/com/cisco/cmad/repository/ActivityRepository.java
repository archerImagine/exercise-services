package com.cisco.cmad.repository;

import java.util.List;

import com.cisco.cmad.model.Activity;
import com.cisco.cmad.model.ActivitySearch;

public interface ActivityRepository {

	List<Activity> findAllActivity();

	Activity findActivity(String activityID);

	void create(Activity activity);

	Activity update(Activity activity);

	void delete(String activityID);

	List<Activity> findByDescription(List<String> description);

	List<Activity> findByDescription(List<String> description, int durationFrom, int durationTo);

	List<Activity> findByConstratints(ActivitySearch search);

}