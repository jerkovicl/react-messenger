package com.messenger.reactmessenger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.validation.Valid;

import com.messenger.reactmessenger.models.ScheduleJob;
import com.messenger.reactmessenger.services.ScheduleJobService;

@RestController
@RequestMapping("/api")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    @GetMapping("/schedulejobs")
    public List<ScheduleJob> all() {
        return scheduleJobService.getAllScheduleJobs();
    }

    // @RequestMapping(value = "/schedulejobs", method = RequestMethod.POST)
    @PostMapping("/schedulejobs")
    public ResponseEntity<ScheduleJob> createScheduleJob(@Valid @RequestBody ScheduleJob scheduleJob) {
        return ResponseEntity.ok(scheduleJobService.saveScheduleJob(scheduleJob));
    }

    @PutMapping("/schedulejobs/{id}")
    public ResponseEntity<ScheduleJob> updateScheduleJob(@Valid @RequestBody ScheduleJob scheduleJob,
            @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(scheduleJobService.updateScheduleJob(scheduleJob, id));
    }

    @DeleteMapping("/schedulejobs/{id}")
    public ResponseEntity<?> deleteScheduleJob(@PathVariable Long id) {
        Map<String, String> response = new HashMap<String, String>();
        if (scheduleJobService.deleteScheduleJob(id)) {
            response.put("status", "success");
            response.put("message", "schedule job deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Something went wrong when deleting schedule job");
            return ResponseEntity.status(500).body(response);
        }
    }
}