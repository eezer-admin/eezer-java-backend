package org.eezer.service.application.service;

import lombok.extern.slf4j.Slf4j;
import org.eezer.api.enums.EezerErrorCode;
import org.eezer.api.exception.EezerException;
import org.eezer.api.request.EezerAddUserRequest;
import org.eezer.api.request.EezerAddVehicleRequest;
import org.eezer.api.request.EezerCreateTokenRequest;
import org.eezer.api.request.EezerEditUserRequest;
import org.eezer.api.request.EezerEditVehicleRequest;
import org.eezer.api.request.EezerStoreTransportRequest;
import org.eezer.api.response.EezerResponse;
import org.eezer.api.valueobject.Token;
import org.eezer.service.domain.exception.InvalidCredentialsException;
import org.eezer.service.domain.exception.InvalidInputException;
import org.eezer.service.domain.exception.RecordNotFoundException;
import org.eezer.service.domain.service.JwtService;
import org.eezer.service.domain.service.TransportService;
import org.eezer.service.domain.service.UserService;
import org.eezer.service.domain.service.VehicleService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Resource
    private UserService userService;

    @Resource
    private VehicleService vehicleService;

    @Resource
    private TransportService transportService;

    @Resource
    private JwtService jwtService;

    /**
     * {@inheritDoc}
     */
    public EezerResponse addUser(EezerAddUserRequest request) {

        try {

            return this.toResponse(userService.addUser(request));
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse removeUser(String username) {

        try {

            userService.removeUser(username);
            return this.toResponse(username);
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse editUser(String username, EezerEditUserRequest request) {

        try {

            return this.toResponse(userService.editUser(username, request));
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse getUsers() {

        try {

            return this.toResponse(userService.getUsers());
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse createToken(EezerCreateTokenRequest credentials) {

        try {
            String token = jwtService.generateAccessToken(credentials.getUsername(),
                    credentials.getPassword());

            return this.toResponse(new Token(token, credentials.getUsername()));

        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse addVehicle(EezerAddVehicleRequest request) {

        try {

            return this.toResponse(vehicleService.addVehicle(request));
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse removeVehicle(String vehicleId) {

        try {

            vehicleService.removeVehicle(vehicleId);
            return this.toResponse(vehicleId);
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse editVehicle(String vehicleId, EezerEditVehicleRequest request) {

        try {

            return this.toResponse(vehicleService.editVehicle(vehicleId, request));
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse getVehicles() {

        try {

            return this.toResponse(vehicleService.getVehicles());
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse storeTransport(EezerStoreTransportRequest request) {

        try {

            return this.toResponse(transportService.storeTransport(request));
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse removeTransport(String transportId) {

        try {

            transportService.removeTransport(transportId);
            return this.toResponse(transportId);
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse getTransports() {

        try {

            return this.toResponse(transportService.getTransports());
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EezerResponse getCoordinates(String transportId) {

        try {

            return this.toResponse(transportService.getCoordinates(transportId));
        } catch (Exception e) {

            throw toError(e);
        }
    }

    /**
     * Build the response object with data.
     *
     * @param data the data to include
     * @return the final response object
     */
    private EezerResponse toResponse(Object data) {

        return EezerResponse.builder()
                .success(true)
                .data(data)
                .build();
    }

    /**
     * Catch exceptions and convert them to a proper Eezer exception.
     *
     * @param e the original exception
     * @return the new Eezer exception
     */
    private EezerException toError(Exception e) {

        log.error("Got an exception when processing request.", e);

        if (e instanceof ConstraintViolationException) {

            throw new EezerException(EezerErrorCode.ValidationError, e.getMessage());
        } else if (e instanceof DuplicateKeyException) {

            throw new EezerException(EezerErrorCode.UniqueIndexError, "identifier (username, vehicleid, transportid) already exists");
        } else if (e instanceof InvalidCredentialsException) {

            throw new EezerException(EezerErrorCode.InvalidUserOrPass, null);
        } else if (e instanceof RecordNotFoundException) {

            throw new EezerException(EezerErrorCode.DocumentNotFound, "document not found");
        } else if (e instanceof InvalidInputException) {
            throw new EezerException(EezerErrorCode.ValidationError, e.getMessage());
        }

        throw new EezerException(EezerErrorCode.Unhandled, "unhandled exception");
    }

}
