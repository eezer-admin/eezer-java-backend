package org.eezer.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.eezer.api.enums.EezerRole;
import org.eezer.api.exception.EezerException;
import org.eezer.api.request.EezerAddVehicleRequest;
import org.eezer.api.request.EezerEditVehicleRequest;
import org.eezer.service.application.service.ApplicationService;
import org.eezer.service.security.annotation.AuthSecured;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
public class VehicleController {

    @Resource
    private ApplicationService applicationService;

    @AuthSecured(roles = EezerRole.ADMIN)
    @RequestMapping(value = "/getvehicles", method = GET)
    public ResponseEntity getAllVehicles() {

        log.info("Received request /getvehicles.");

        try {

            return ResponseEntity.ok(applicationService.getVehicles());
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

    @AuthSecured(roles = EezerRole.ADMIN)
    @RequestMapping(value = "/addvehicle", method = POST)
    public ResponseEntity addVehicle(@RequestBody EezerAddVehicleRequest request) {

        log.info("Received request /addvehicle.");

        try {

            return ResponseEntity.ok(applicationService.addVehicle(request));
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

    @AuthSecured(roles = EezerRole.ADMIN)
    @RequestMapping(value = "/editvehicle/{vehicleId}", method = POST)
    public ResponseEntity editVehicle(@PathVariable(value = "vehicleId") String vehicleId,
                                   @RequestBody EezerEditVehicleRequest request) {

        log.info("Received request /editvehicle.");

        try {

            return ResponseEntity.ok(applicationService.editVehicle(vehicleId, request));
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

    @AuthSecured(roles = EezerRole.ADMIN)
    @RequestMapping(value = "/rmvehicle/{vehicleId}", method = DELETE)
    public ResponseEntity removeVehicle(@PathVariable(value = "vehicleId") String vehicleId) {

        log.info("Received request /rmvehicle.");

        try {

            return ResponseEntity.ok(applicationService.removeVehicle(vehicleId));
        } catch (EezerException e) {

            return ResponseEntity.badRequest().body(e.getError());
        } catch (Exception e) {

            log.error("Got unhandled exception.", e);
            throw e;
        }
    }

}
