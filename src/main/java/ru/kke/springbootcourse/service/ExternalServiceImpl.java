package ru.kke.springbootcourse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kke.springbootcourse.model.ExternalInfo;

@Slf4j
@Service
class ExternalServiceImpl implements ExternalService {

    @Override
    public ExternalInfo getInfo() {
        log.debug("call getInfo");
        return ExternalInfo.builder().info("INFO").build();
    }

}
