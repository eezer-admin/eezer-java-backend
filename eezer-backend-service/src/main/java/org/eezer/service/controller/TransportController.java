package org.eezer.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.eezer.api.enums.EezerRole;
import org.eezer.api.exception.EezerException;
import org.eezer.api.request.EezerStoreTransportRequest;
import org.eezer.service.application.service.ApplicationService;
import org.eezer.service.security.annotation.AuthSecured;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@CrossOrigin
@RestController
public class TransportController {

    @Resource
    private ApplicationService applicationService;

    @AuthSecured(roles = EezerRole.ADMIN)
    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity getAllUsers() {

        log.info("Received request /all.");

        try {

            return ResponseEntity.ok(applicationService.getTransports());
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

    @AuthSecured(roles = { EezerRole.ADMIN, EezerRole.DRIVER })
    @RequestMapping(value = "/store", method = POST)
    public ResponseEntity addUser(@RequestBody EezerStoreTransportRequest request) {

        log.info("Received request /store.");

        try {

            return ResponseEntity.ok(applicationService.storeTransport(request));
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

    @AuthSecured(roles = EezerRole.ADMIN)
    @RequestMapping(value = "/remove/{transportId}", method = DELETE)
    public ResponseEntity removeTransport(@PathVariable(value = "transportId") String transportId) {

        log.info("Received request /remove.");

        try {

            return ResponseEntity.ok(applicationService.removeTransport(transportId));
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

    @AuthSecured(roles = EezerRole.ADMIN)
    @RequestMapping(value = "/coords/{transportId}", method = GET)
    public ResponseEntity getCoordinates(@PathVariable(value = "transportId") String transportId) {

        log.info("Received request /coords.");

        try {

            return ResponseEntity.ok(applicationService.getCoordinates(transportId));
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

}
