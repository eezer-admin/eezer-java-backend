/*
package org.eezer.service.domain.service;

import org.eezer.api.enums.EezerRole;
import org.eezer.service.config.SpringUnitTest;
import org.eezer.service.domain.model.UserModel;
import org.eezer.service.domain.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserServiceTest extends SpringUnitTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testRepository() {

        UserModel userModel = new UserModel(null,
                "testuser", "testpass",
                EezerRole.ADMIN, "real name",
                "07399999", "a@a.a", "org",
                "other stuff", Collections.emptySet(), new Date().toString());

        userRepository.save(userModel);

        List<UserModel> users = userRepository.findAll();

        Assert.assertEquals(1, users.size());
    }

}
*/
