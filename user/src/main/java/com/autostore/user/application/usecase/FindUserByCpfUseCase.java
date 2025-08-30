package com.autostore.user.application.usecase;


import com.autostore.user.application.port.driver.FindUserByCpfDriverPort;
import com.autostore.user.application.port.driver.model.query.FindUserByCpfQuery;
import com.autostore.user.application.port.driver.model.query.FindUserByCpfQueryOutput;


public class FindUserByCpfUseCase implements FindUserByCpfDriverPort {

    @Override
    public FindUserByCpfQueryOutput execute(FindUserByCpfQuery query) {
        return null;
    }

}
