package org.eezer.service.domain.service;

import org.eezer.api.enums.EezerRole;
import org.eezer.api.request.EezerAddUserRequest;
import org.eezer.api.request.EezerEditUserRequest;
import org.eezer.api.valueobject.User;
import org.eezer.service.domain.exception.InvalidInputException;
import org.eezer.service.domain.exception.RecordNotFoundException;
import org.eezer.service.domain.model.UserModel;
import org.eezer.service.domain.model.VehicleModel;
import org.eezer.service.domain.repository.UserRepository;
import org.eezer.service.domain.repository.VehicleRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Default implementation of the {@link UserService} interface.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    @Resource
    private ConversionService conversionService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private void verifyVehicleIds(Set<String> vehicles) {
        List<VehicleModel> vehicleModels = vehicleRepository.findAll();

        List<String> allVehicles = vehicleModels.stream()
                .map(VehicleModel::getVehicleId)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        vehicles.forEach(vehicle -> {
            if (!allVehicles.contains(vehicle.toUpperCase())) {
                throw new InvalidInputException("One or more of the entered vehicle ids are incorrect.");
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User addUser(@NotNull EezerAddUserRequest request) {

        try {
            // check that the given role exists
            EezerRole role = EezerRole.valueOf(request.getRole().toUpperCase());
            request.setRole(role.name());
        } catch (Exception e) {
            throw new InvalidInputException("Role must be either ADMIN or DRIVER.");
        }

        // check that given vehicles (if any) exists
        verifyVehicleIds(request.getVehicles());

        UserModel userModel = conversionService.convert(request, UserModel.class);

        if (userModel == null) {
            throw new InvalidInputException(("An unexpected error occurred. Could not parse request."));
        }

        userModel.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userRepository.save(userModel);

        return conversionService.convert(userModel, User.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUser(String username) {

        UserModel user = userRepository.getByUsername(username);

        if (user != null) {
            userRepository.delete(user);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User editUser(String username, EezerEditUserRequest request) {

        UserModel user = userRepository.getByUsername(username);

        if (user != null) {

            verifyVehicleIds(request.getVehicles());

            UserModel editedUser = user.toBuilder()
                    .realName(request.getRealName() != null ? request.getRealName() : user.getRealName())
                    .phone(request.getPhone() != null ? request.getPhone() : user.getPhone())
                    .email(request.getEmail() != null ? request.getEmail() : user.getEmail())
                    .organization(request.getOrganization() != null ? request.getOrganization() : user.getOrganization())
                    .other(request.getOther() != null ? request.getOther() : user.getOther())
                    .vehicles(request.getVehicles() != null ? request.getVehicles() : user.getVehicles())
                    .build();

            userRepository.save(editedUser);
            return conversionService.convert(editedUser, User.class);
        }

        throw new RecordNotFoundException("No user found with username " + username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsers() {

        List<UserModel> userModelList = userRepository.findAll();
        return Arrays.asList(conversionService.convert(userModelList, User[].class));
    }

}
