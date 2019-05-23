package com.messenger.reactmessenger.repositories;

import com.messenger.reactmessenger.repositories.CrudRepository;

import java.util.List;

import com.messenger.reactmessenger.models.ScheduleJob;

public interface ScheduleJobRepository extends CrudRepository<ScheduleJob, Long> {
    List<ScheduleJob> findByStatus(Boolean status);
}