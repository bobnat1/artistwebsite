package com.project.capstone;

import com.project.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Modifying
    @Transactional
    @Query("UPDATE user_roles u SET u.role = ?2 WHERE u.user_id = ?1")
    public static void changeUsersRole(Integer roleId, Integer newRoleId) {

    }
}
