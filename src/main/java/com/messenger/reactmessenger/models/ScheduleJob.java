package com.messenger.reactmessenger.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.messenger.reactmessenger.ScheduleJobEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(ScheduleJobEntityListener.class)
@Table(name = "schedule_jobs")
public class ScheduleJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "message_text")
    @NotEmpty(message = "* Please Enter Message Text")
    private String messageText;

    @Column(name = "channel")
    private String channel;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "time", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

}
