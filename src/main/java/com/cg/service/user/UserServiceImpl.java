package com.cg.service.user;

import com.cg.model.*;
import com.cg.model.dto.user.*;
import com.cg.repository.UserRepository;
import com.cg.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public UserCreResDTO create(UserCreReqDTO userCreReqDTO) {
        Optional<Role> role = roleService.findById(Long.valueOf(2L));
        User user = userCreReqDTO.toUser(role.get());
        user.setRole(role.get());
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        UserCreResDTO userCreResDTO = user.toUserCreResDTO();
        return userCreResDTO;
    }

    @Override
    public Boolean existsByUsernameAndIdNot(String username, Long id) {
        return userRepository.existsByUsernameAndIdNot(username, id);
    }


    @Override
    public Boolean existsByEmailAndIdNot(String email, Long id) {
        return userRepository.existsByEmailAndIdNot(email, id);
    }

    @Override
    public UserUpResDTO update(long userId, UserUpReqDTO userUpReqDTO) {
        User user = userUpReqDTO.toUser(userId, roleService.findById(Long.valueOf(2L)).get());

        user.setUsername(userUpReqDTO.getUsername());
        user.setPassword(userUpReqDTO.getPassword());
        user.setFullName(userUpReqDTO.getFullName());
        user.setEmail(userUpReqDTO.getEmail());
        user.setPhone(userUpReqDTO.getPhone());
        user.setAddress(userUpReqDTO.getAddress());

        userRepository.save(user);

        UserUpResDTO userUpResDTO = user.toUserUpResDTO();

        return userUpResDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    public List<UserDTO> findAllUserDTOS() {
        return userRepository.findAllUserDTOS();
    }

    public List<User> findAllByDeletedIs(Boolean boo) {
        return userRepository.findAllByDeletedIs(boo);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
