package com.autostore.inventory.application.port.driven;


import com.autostore.inventory.domain.Activity;

import java.util.List;


public interface ActivityRepository {

    void save(Activity activity);

    List<Activity> findByOrderIdAndTransactionId(String orderId, String transactionId);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);

}
