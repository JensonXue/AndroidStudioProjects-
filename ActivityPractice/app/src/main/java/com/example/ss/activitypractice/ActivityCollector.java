package com.example.ss.activitypractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    public  static List<Activity> activities = new ArrayList<>();
    public  static void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    public  static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    public static void finshAll()
    {
        for (Activity activity1 : activities)
            if (!activity1.isFinishing())
                activity1.finish();
    }
}