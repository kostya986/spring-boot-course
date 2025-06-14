package ru.kke.springbootcourse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class ExternalServiceImpl implements ExternalService {

    @Override
    public String getInfo() {
        log.debug("call getInfo");
        return "INFO";
    }

}
