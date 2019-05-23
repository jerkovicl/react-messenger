package com.messenger.reactmessenger;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.messenger.reactmessenger.models.ScheduleJob;
import com.messenger.reactmessenger.models.SlackMessage;
import com.messenger.reactmessenger.services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
public class ScheduleJobEntityListener {

    static private SchedulerService schedulerService;

    @Autowired(required = true)
    @Qualifier("schedulerService")
    public void setSearchService(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PrePersist
    public void scheduleJobPrePersist(ScheduleJob sj) {
        System.out.println("Listening ScheduleJob Pre Persist : " + sj.getTime());
    }

    @PostPersist
    public void scheduleJobPostPersist(ScheduleJob sj) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        SlackMessage slackMessage = SlackMessage.builder().id(sj.getId()).text(sj.getMessageText()).build();
        System.out.println("Listening ScheduleJob Post Persist : " + sj.getTime() + slackMessage.getText());
        schedulerService.addTaskToScheduler(sj.getTime(), slackMessage);
    }

    @PostLoad
    public void scheduleJobPostLoad(ScheduleJob sj) {
        System.out.println("Listening ScheduleJob Post Load : " + sj.getTime());
    }

    @PreUpdate
    public void scheduleJobPreUpdate(ScheduleJob sj) {
        System.out.println("Listening ScheduleJob Pre Update : " + sj.getTime());
    }

    @PostUpdate
    public void scheduleJobPostUpdate(ScheduleJob sj) {
        System.out.println("Listening ScheduleJob Post Update : " + sj.getTime());
    }

    @PreRemove
    public void scheduleJobPreRemove(ScheduleJob sj) {
        System.out.println("Listening ScheduleJob Pre Remove : " + sj.getTime());
    }

    @PostRemove
    public void scheduleJobPostRemove(ScheduleJob sj) {
        System.out.println("Listening ScheduleJob Post Remove : " + sj.getId());
        schedulerService.removeTaskFromScheduler(sj.getId());
    }
}