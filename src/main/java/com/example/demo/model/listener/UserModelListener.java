package com.example.demo.model.listener;

import com.example.demo.model.User;
import com.example.demo.service.DatabaseSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class UserModelListener extends AbstractMongoEventListener<User> {

    @Autowired
    DatabaseSequenceService databaseSequenceService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {
        if (event.getSource().getUserId() < 1) {
            event.getSource().setUserId(databaseSequenceService.generateSequence(User.SEQUENCE_NAME));
        }
    }
}
