package com.ets.bree.controllers;

import com.ets.bree.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    @Autowired
    private RegisterService service;


}
