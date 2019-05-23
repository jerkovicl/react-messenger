package com.messenger.reactmessenger;

import com.messenger.reactmessenger.models.SlackMessage;
import com.messenger.reactmessenger.repositories.ScheduleJobRepository;
import com.messenger.reactmessenger.services.ScheduleJobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackUtils {

    @Autowired
    private ScheduleJobService scheduleJobService;
    private String slackWebhookUrl;

    public void sendMessage(SlackMessage message) {

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        slackWebhookUrl = System.getenv("SLACK_HOOKURL");
        HttpHeaders headers = new HttpHeaders();
        // headers.add("Accept", MimeTypeUtils.APPLICATION_JSON_VALUE);
        // headers.add("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE);
        final HttpEntity<SlackMessage> entity = new HttpEntity<SlackMessage>(message, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(slackWebhookUrl, entity, String.class);
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            System.out.println(statusCode.toString() + response);
            scheduleJobService.updateScheduleJobStatus(message.getId());
        }

    }

}
