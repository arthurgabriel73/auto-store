package com.autostore.bff.application.port.driver;


import com.autostore.bff.domain.DomainEvent;
import com.autostore.bff.domain.Topic;


public interface OrchestratorServiceDriverPort {

    void consumeSagaEvent(DomainEvent<?> event, Topic topic);

}
