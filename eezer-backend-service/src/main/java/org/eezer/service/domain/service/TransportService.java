package org.eezer.service.domain.service;

import org.eezer.api.request.EezerStoreTransportRequest;
import org.eezer.api.valueobject.Coordinate;
import org.eezer.api.valueobject.Transport;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The transport service is responsible of handling all transport related requests.
 */
public interface TransportService {

    /**
     * Create and persist a new transport.
     *
     * @param request the new transport data
     *
     * @return the new transport object, if successful
     */
    Transport storeTransport(@NotNull EezerStoreTransportRequest request);

    /**
     * Remove an existing transport by it's transportId.
     *
     * !!! NOTE: USUALLY WE DON'T DO THIS !!!
     *
     * @param transportId the transportId to remove
     */
    void removeTransport(@NotNull String transportId);

    /**
     * Fetch all existing transports in the system.
     * We don't return coordinates here, there is a
     * separate call for that.
     *
     * @return a list of all existing transports
     */
    List<Transport> getTransports();

    /**
     * Fetch all coordinates associated with a
     * specific transport.
     *
     * @param transportId the transportId
     *
     * @return a list of all coordinates
     */
    List<Coordinate> getCoordinates(@NotNull String transportId);

}
