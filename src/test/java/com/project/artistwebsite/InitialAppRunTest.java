package com.project.artistwebsite;

import com.project.artistwebsite.repository.RoleRepository;
import com.project.artistwebsite.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InitialAppRunTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    /*

    *Setting up User Account*
    Step 1: Run the ArtistWebsiteApplication to set up Database
    Step 2: Run InitialAppRunTest so the method setRolesInRepositoryTest will input ROLE_ADMIN and ROLE_USER into Database
    Step 3: Run ArtistWebsiteApplication and register with email and password

    *Changing initial User Account to Admin*
    Step 1: Comment out setRolesInRepositoryTest and uncomment changeUserRoleTest then run InitialAppRunTest again

     */

    @Test
    public void setRolesInRepositoryTest() {

        roleRepository.addUserRoleToTable();
        roleRepository.addAdminRoleToTable();

    }

//    @Test
//    public void changeUserRoleTest() {
//
//        userRepository.changeUsersRole(1, 2);
//    }
}
