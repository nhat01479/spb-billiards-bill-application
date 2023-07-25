package com.cg.service.user;

import com.cg.model.Desk;
import com.cg.model.User;
import com.cg.model.dto.desk.*;
import com.cg.model.dto.user.*;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IGeneralService<User, Long>, UserDetailsService {
    Boolean existsByUsername(String username);

    User getByUsername(String username);

    UserCreResDTO create(UserCreReqDTO userCreReqDTO);

    Boolean existsByUsernameAndIdNot(String username, Long id);

    Boolean existsByEmailAndIdNot(String email, Long id);


    UserUpResDTO update(long userId, UserUpReqDTO userUpReqDTO);

    List<UserDTO> findAllUserDTOS();

    List<User> findAllByDeletedIs(Boolean boo);

    Boolean existsByEmail(String email);
}
