package com.autostore.user.application.port.driver;


import com.autostore.user.application.port.driver.model.query.FindUserByCpfQuery;
import com.autostore.user.application.port.driver.model.query.FindUserByCpfQueryOutput;


public interface FindUserByCpfDriverPort {

    FindUserByCpfQueryOutput execute(FindUserByCpfQuery query);

}
