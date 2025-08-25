package ru.kke.springbootcourse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kke.springbootcourse.model.ExternalInfo;
import ru.kke.springbootcourse.service.ExternalService;

@RequiredArgsConstructor
@RestController
class DefaultController {

    private final ExternalService externalService;

    @GetMapping(value = "/")
    public ExternalInfo getInfo() {
        return externalService.getInfo();
    }

}
