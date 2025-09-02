package com.autostore.user.application.port.driven;


public interface TokenGenerator {

    String generateTokenWithCpf(String userIdentifier);

}
