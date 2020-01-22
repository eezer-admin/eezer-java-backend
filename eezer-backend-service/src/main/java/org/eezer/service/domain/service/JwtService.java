package org.eezer.service.domain.service;

import org.eezer.service.domain.exception.InvalidTokenException;

import java.util.Map;

/**
 * Interface for the JWT service.
 */
public interface JwtService {

	/**
	 * Generates a new access token for a specific username.
	 *
	 * @param username the username to create token for
	 * @param password the password
     *
	 * @return the access token
	 */
	String generateAccessToken(String username, String password);

	/**
	 * Validate a signed access token (jwt).
	 *
	 * @param token the access token to validate
     * @param roles user access roles
     *
	 * @return the decoded payload if successful
	 */
	Map<String, Object> validateAccessToken(String token, String[] roles);

	/**
	 * Get the configured token validity time (in seconds).
	 *
	 * @return token validity time in seconds.
	 */
	Long getTokenValidityTime();

    /**
     * Extract the JWT token from the Authentication header.
     *
     * @param header the complete authentication header
     * @return the token part if successful
     *
     * @throws InvalidTokenException if unsuccessful
     */
    String getTokenFromAuthHeader(String header);

}
