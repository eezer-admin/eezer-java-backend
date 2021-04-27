package org.eezer.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.eezer.api.exception.EezerException;
import org.eezer.api.request.EezerCreateTokenRequest;
import org.eezer.api.response.EezerVersionResponse;
import org.eezer.service.application.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class AdminController {

    @Resource
    private ApplicationService applicationService;

    @RequestMapping(value = "/", method = GET)
    public ResponseEntity hello() {

        EezerVersionResponse response = new EezerVersionResponse(
                "Welceome to Eezer API", "1.0.4");

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity loginUser(@RequestBody EezerCreateTokenRequest request) {

        log.info("Received request /login.");

        try {

            return ResponseEntity.ok(applicationService.createToken(request));
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

}
