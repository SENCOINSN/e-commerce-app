package com.sid.ecommerce.repository;

import com.sid.ecommerce.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
