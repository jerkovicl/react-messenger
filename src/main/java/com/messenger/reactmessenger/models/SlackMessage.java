package com.messenger.reactmessenger.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

@AllArgsConstructor
@Builder(builderClassName = "Builder")
@Getter
@Setter
public class SlackMessage implements Serializable {

    @Id
    private Long id;
    private String text;
    private Date time;

}