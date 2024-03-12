package com.empbackend.employee.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empbackend.employee.model.Message;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dev/employee-api/")
public class QueueSendController {
    private static final String QUEUE_NAME = "test";
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/queue")
    public String postMessage(@RequestBody Message message) {
        JSONObject jsonMessage = new JSONObject(message);
        String jsonString = jsonMessage.toString();
        try {
            jmsTemplate.convertAndSend(QUEUE_NAME, jsonString);
            return jsonString;
        } catch (Exception e) {
            System.out.println(e.toString());
            return e.toString();
        }
    }
}
