package com.imt.intes.job;

import com.imt.intes.configuration.security.Role;
import com.imt.intes.model.UserEntity;
import com.imt.intes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeDataJob implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Initialization of data start");

        UserEntity adminUser = new UserEntity();
        adminUser.setLogin("admin");
        adminUser.setPassword("azerty");
        adminUser.setAddress(null);
        adminUser.getRoles().add(Role.ROLE_ADMIN);
        userService.createNewUser(adminUser);

        UserEntity adminManagerUser = new UserEntity();
        adminManagerUser.setLogin("admin-manager");
        adminManagerUser.setPassword("azerty");
        adminManagerUser.setAddress(null);
        adminManagerUser.getRoles().add(Role.ROLE_ADMIN);
        adminManagerUser.getRoles().add(Role.ROLE_MANAGER);
        userService.createNewUser(adminManagerUser);

        UserEntity managerUser = new UserEntity();
        managerUser.setLogin("manager");
        managerUser.setPassword("azerty");
        managerUser.setAddress(null);
        managerUser.getRoles().add(Role.ROLE_MANAGER);
        userService.createNewUser(managerUser);

        UserEntity simpleUser = new UserEntity();
        simpleUser.setLogin("user");
        simpleUser.setPassword("azerty");
        simpleUser.setAddress(null);
        simpleUser.getRoles().add(Role.ROLE_USER);
        userService.createNewUser(simpleUser);

        logger.info("Initialization of data end");
    }
}
