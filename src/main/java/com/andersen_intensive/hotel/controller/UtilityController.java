package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/utility")
public class UtilityController {
    private final UtilityService utilityService;

    @PostMapping("")
    public long addClient(@RequestBody Utility utility) {
        utilityService.saveUtility(utility);
        return utility.getId();
    }
}
