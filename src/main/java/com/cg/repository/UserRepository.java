package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    Optional<User> findByUsername(String username);

    User getByUsername(String username);


    List<User> findAllByDeletedIs(Boolean boo);


    Boolean existsByUsernameAndIdNot(String username, Long id);

    Boolean existsByEmailAndIdNot(String email, Long id);


    @Query("SELECT NEW com.cg.model.dto.user.UserDTO (" +
            "us.id, " +
            "us.username, " +
            "us.password," +
            "us.fullName," +
            "us.email, " +
            "us.phone," +
            "us.address," +
            "us.role" +
            ") " +
            "FROM User AS us " +
            "where us.deleted=false "
    )
    List<UserDTO> findAllUserDTOS();

    @Query("select new com.cg.model.dto.user.UserDTO (" +
            "u.id, " +
            "u.username, " +
            "u.password, " +
            "u.fullName, " +
            "u.email, " +
            "u.phone," +
            "u.address," +
            "u.role)" +
            " from User as u where (u.username like %:username% or u.fullName like %:fullName%) and u.deleted=false")
    List<UserDTO> findAllByDeletedFalseAndUserNameLikeAndFullNameLike(String username, String fullName);
}
