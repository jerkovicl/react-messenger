package com.messenger.reactmessenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

import com.messenger.reactmessenger.models.ScheduleJob;
import com.messenger.reactmessenger.repositories.ScheduleJobRepository;

@Service("scheduleJobService")
public class ScheduleJobService {
    @Autowired
    private ScheduleJobRepository scheduleJobRepository;

    public List<ScheduleJob> getAllScheduleJobs() {
        return scheduleJobRepository.findByStatus(false);
    }

    public ScheduleJob saveScheduleJob(ScheduleJob scheduleJob) {
        return scheduleJobRepository.save(scheduleJob);
    }

    public ScheduleJob updateScheduleJob(ScheduleJob scheduleJob, Long id) {
        ScheduleJob updateScheduleJob = scheduleJobRepository.findById(id).orElse(null);
        if (updateScheduleJob != null) {
            updateScheduleJob.setMessageText(scheduleJob.getMessageText());
            updateScheduleJob.setTime(scheduleJob.getTime());
        }
        final ScheduleJob scheduleJobDb = scheduleJobRepository.save(updateScheduleJob);
        return scheduleJobDb;
    }

    public ScheduleJob updateScheduleJobStatus(Long id) {
        ScheduleJob updateScheduleJob = scheduleJobRepository.findById(id).orElse(null);
        if (updateScheduleJob != null) {
            updateScheduleJob.setStatus(true);

        }
        final ScheduleJob scheduleJobDb = scheduleJobRepository.save(updateScheduleJob);
        return scheduleJobDb;
    }

    public Boolean deleteScheduleJob(Long id) {
        ScheduleJob delScheduleJob = scheduleJobRepository.findById(id).orElse(null);
        if (delScheduleJob != null) {
            scheduleJobRepository.delete(delScheduleJob);
            return true;
        }
        return false;
    }
}