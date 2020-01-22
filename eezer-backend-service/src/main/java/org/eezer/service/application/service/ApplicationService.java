package org.eezer.service.application.service;

import org.eezer.api.exception.EezerException;
import org.eezer.api.request.EezerAddUserRequest;
import org.eezer.api.request.EezerAddVehicleRequest;
import org.eezer.api.request.EezerCreateTokenRequest;
import org.eezer.api.request.EezerEditUserRequest;
import org.eezer.api.request.EezerEditVehicleRequest;
import org.eezer.api.request.EezerStoreTransportRequest;
import org.eezer.api.response.EezerResponse;

public interface ApplicationService {

    /*
     * USER REQUESTS
     */

    /**
     * Handles a "add user" request.
     *
     * @param request the add user request containing
     *                the user new data
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse addUser(EezerAddUserRequest request);

    /**
     * Handles a "remove user" request.
     *
     * @param username the username to remove
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse removeUser(String username);

    /**
     * Handles a "edit user" request.
     *
     * @param username the username to edit
     * @param request the edited user details
     * @return a response object if successful
     */
    EezerResponse editUser(String username, EezerEditUserRequest request);

    /**
     * Handles a "get users" request.
     *
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse getUsers();

    /**
     * Create a new token for a user.
     *
     * @param credentials the user credentials
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse createToken(EezerCreateTokenRequest credentials);

    /*
     * VEHICLE REQUESTS
     */

    /**
     * Handles a "add vehicle" request.
     *
     * @param request the add vehicle request containing
     *                the new vehicle data
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse addVehicle(EezerAddVehicleRequest request);

    /**
     * Handles a "remove vehicle" request.
     *
     * @param vehicleId the vehicle id to remove
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse removeVehicle(String vehicleId);

    /**
     * Handles a "edit vehicle" request.
     *
     * @param vehicleId the vehicleId to edit
     * @param request the edited vehicle details
     * @return a response object if successful
     */
    EezerResponse editVehicle(String vehicleId, EezerEditVehicleRequest request);

    /**
     * Handles a "get vehicles" request.
     *
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse getVehicles();

    /*
     * TRANSPORT REQUESTS
     */

    /**
     * Handles a "store transport" request.
     *
     * @param request the store transport request
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse storeTransport(EezerStoreTransportRequest request);

    /**
     * Handles a "remove transport" request.
     *
     * @param transportId the transport id to remove
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse removeTransport(String transportId);

    /**
     * Handles a "get transports" request.
     *
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse getTransports();

    /**
     * Handles a "get coordinates" request.
     *
     * @param transportId the transport id to
     *                    fetch coordinates for
     *
     * @return a response object if successful
     *
     * @throws EezerException if unsuccessful
     */
    EezerResponse getCoordinates(String transportId);
}
