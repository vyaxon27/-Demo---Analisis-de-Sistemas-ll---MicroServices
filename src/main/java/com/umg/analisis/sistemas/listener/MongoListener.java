package com.umg.analisis.sistemas.listener;

import com.umg.analisis.sistemas.document.Customer;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MongoListener extends AbstractMongoEventListener<Customer> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Customer> event) {
        Customer notification = event.getSource();
        setCreatedOrModifiedDate(notification);
    }

    private void setCreatedOrModifiedDate(Customer notification) {
        if (notification.getCreatedAt() == null) {
            notification.setCreatedAt(LocalDateTime.now());
        } else {
            notification.setModifiedAt(LocalDateTime.now());
        }
    }

}

