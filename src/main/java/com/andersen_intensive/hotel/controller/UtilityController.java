package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.service.UtilityService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {
    private final UtilityService utilityService;

    public UtilityController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
