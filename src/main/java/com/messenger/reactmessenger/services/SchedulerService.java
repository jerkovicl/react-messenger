package com.messenger.reactmessenger.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import com.messenger.reactmessenger.ScheduledTasks;
import com.messenger.reactmessenger.SlackUtils;
import com.messenger.reactmessenger.models.SlackMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
public class SchedulerService {

    // Task Scheduler
    @Autowired
    TaskScheduler scheduler;
    SlackUtils slackUtils;

    // A map for keeping scheduled tasks
    Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public SchedulerService(TaskScheduler scheduler, SlackUtils slackUtils) {
        this.scheduler = scheduler;
        this.slackUtils = slackUtils;
    }

    // Schedule Task to be executed at specific time
    public void addTaskToScheduler(Date date, SlackMessage slackMessage) {
        Runnable task = new Runnable() {

            public void run() {

                slackUtils.sendMessage(slackMessage);
            }
        };
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, date);
        jobsMap.put(slackMessage.getId(), scheduledTask);
    }

    // Remove scheduled task
    public void removeTaskFromScheduler(Long id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }
    }

    // A context refresh event listener
    @EventListener({ ContextRefreshedEvent.class })
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }

}