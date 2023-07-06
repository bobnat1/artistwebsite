package com.project.capstone.repository;

import com.project.capstone.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_roles SET role_id = ?2 WHERE user_id = ?1", nativeQuery = true)
    public int changeUsersRole(Integer userId, Integer newRoleId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user WHERE id = ?1", nativeQuery = true)
    public int removeUser(Integer userId);
}
