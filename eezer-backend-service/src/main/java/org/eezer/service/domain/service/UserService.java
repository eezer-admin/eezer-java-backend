package org.eezer.service.domain.service;

import org.eezer.api.request.EezerAddUserRequest;
import org.eezer.api.request.EezerEditUserRequest;
import org.eezer.api.valueobject.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The user service is responsible of handling all user requests,
 * such as add user, remove user and edit user.
 */
public interface UserService {

    /**
     * Create and persist a new user.
     *
     * @param request the new users data
     *
     * @return the new user object, if successful
     */
    User addUser(@NotNull EezerAddUserRequest request);

    /**
     * Remove an existing user by it's username,
     *
     * @param username the username to remove
     */
    void removeUser(@NotNull String username);

    /**
     * Edit an existing user.
     *
     * @param username the username to edit
     * @param request the edited user data
     * @return the edited user object
     */
    User editUser(@NotNull String username, @NotNull EezerEditUserRequest request);

    /**
     * Fetch all existing users in the system.
     *
     * @return a list of all existing users
     */
    List<User> getUsers();

}
