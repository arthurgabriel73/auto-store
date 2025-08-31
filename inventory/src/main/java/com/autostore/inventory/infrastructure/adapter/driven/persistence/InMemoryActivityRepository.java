package com.autostore.inventory.infrastructure.adapter.driven.persistence;


import com.autostore.inventory.application.port.driven.ActivityRepository;
import com.autostore.inventory.domain.Activity;

import java.util.ArrayList;
import java.util.List;


public class InMemoryActivityRepository implements ActivityRepository {

    List<Activity> activities = new ArrayList<>();


    @Override
    public Activity save(Activity activity) {
        Activity newActivity = toNewInstance(activity);
        activities.add(newActivity);
        return toNewInstance(newActivity);
    }

    @Override
    public List<Activity> findByOrderIdAndTransactionId(String orderId, String transactionId) {
        return activities
                .stream()
                .filter(activity -> activity.orderId().equals(orderId) && activity.transactionId().equals(transactionId))
                .map(this::toNewInstance)
                .toList();
    }

    @Override
    public Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId) {
        return activities
                .stream()
                .anyMatch(a -> a.orderId().equals(orderId) && a.transactionId().equals(transactionId));
    }

    private Activity toNewInstance(Activity activity) {
        return Activity
                .builder()
                .id(activity.id() != null ? activity.id() : activities.size() + 1)
                .inventory(activity.inventory())
                .orderId(activity.orderId())
                .transactionId(activity.transactionId())
                .orderQuantity(activity.orderQuantity())
                .oldQuantity(activity.oldQuantity())
                .newQuantity(activity.newQuantity())
                .createdAt(activity.createdAt())
                .updatedAt(activity.updatedAt())
                .build();
    }

}
