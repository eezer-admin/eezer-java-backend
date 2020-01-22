package org.eezer.service.domain.service;

import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.eezer.service.domain.exception.InvalidCredentialsException;
import org.eezer.service.domain.exception.InvalidTokenException;
import org.eezer.service.domain.exception.TokenHasExpiredException;
import org.eezer.service.domain.model.UserModel;
import org.eezer.service.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Default implementation of the JwtService.
 *
 * Uses java-jwt from auth0 (https://github.com/auth0/java-jwt).
 */
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

	private static final long DEFAULT_JWT_VALIDITY_TIME_IN_SEC = 3600;
	private static final String DEFAULT_ISSUER = "org.eezer";
	private static final String DEFAULT_SECRET = "superSecret123!";

	private static final String JWT_ISSUER = "iss";
	private static final String JWT_EXPIRATION = "exp";
	private static final String JWT_SUBJECT = "sub";
	private static final String JWT_ROLE = "role";

	private static final String TOKEN_PREFIX = "Bearer";

	@Value("${org.eezer.default-token-validity-time:" + DEFAULT_JWT_VALIDITY_TIME_IN_SEC + "}")
	private long jwtValidityTime;

	@Value("${org.eezer.issuer:" + DEFAULT_ISSUER + "}")
	private String issuer;

    @Value("${org.eezer.client_secret:" + DEFAULT_SECRET + "}")
    private String clientSecret;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generateAccessToken(String username, String password) {
		JWTSigner j = new JWTSigner(clientSecret);

		Map<String, Object> claims = new HashMap<>();

		UserModel userModel = userRepository.getByUsername(username);

		if (userModel != null && bCryptPasswordEncoder.matches(
		        password, userModel.getPassword())) {

			claims.put(JWT_ISSUER, issuer);
			claims.put(JWT_EXPIRATION, getExpiryDate(jwtValidityTime));
			claims.put(JWT_SUBJECT, username);
			claims.put(JWT_ROLE, userModel.getRole().name());

			return j.sign(claims);
		}

		throw new InvalidCredentialsException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> validateAccessToken(String token, String[] roles) {

		Map<String, Object> decodedPayload;

		try {

			decodedPayload = new JWTVerifier(clientSecret).verify(token);

			// Check that the user belongs to the player.

            String username = (String) decodedPayload.get(JWT_SUBJECT);
            String desiredRole = (String) decodedPayload.get(JWT_ROLE);
            UserModel userModel = userRepository.getByUsername(username);

            if (userModel == null) {

                log.warn("No user found for token {}.", token);
                throw new InvalidTokenException();
            }

            if (!Arrays.asList(roles).contains(desiredRole)) {

                log.warn("This operation requires user role {} but was not found for token {}", desiredRole, token);
                throw new InvalidTokenException();
            }

		} catch (JWTExpiredException e) {
			// token has expired
			log.warn("Invalid token {}, token has expired.", token);
			throw new TokenHasExpiredException();
		} catch (Exception e) {
			log.warn("Invalid token {}, token has been tampered with or is invalid.", token);
			throw new InvalidTokenException();
		}

		return decodedPayload;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getTokenValidityTime() {
		return jwtValidityTime;
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public String getTokenFromAuthHeader(String header) {

        if (header != null) {

            String[] parts = header.split(" ");

            if (parts.length == 2 && parts[0].equals(TOKEN_PREFIX)) {
                return parts[1];
            }
        }

        throw new InvalidTokenException();
    }

	/**
	 * Calculate an expiry date from seconds.
	 *
	 * @param secondsFromNow number of seconds from now
	 * @return the expiry date
	 */
	private long getExpiryDate(long secondsFromNow)
	{
		Calendar expDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		return (expDate.getTimeInMillis() / 1000L) + secondsFromNow;
	}

}
