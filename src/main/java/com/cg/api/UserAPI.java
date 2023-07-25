package com.cg.api;

import java.text.Normalizer;
import java.util.regex.Pattern;

import com.cg.model.User;
import com.cg.model.dto.user.*;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import com.cg.ultis.AppUtils;
import com.cg.ultis.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    private IUserService userService;

    @Autowired
    private AppUtils appUtils;
    @GetMapping
    public ResponseEntity<?> getAllUsers() {

        List<UserDTO> userDTOS = userService.findAllUserDTOS();

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable Long userId) {

        Optional<User> userOptional = userService.findById(userId);

        if (userOptional.isEmpty()) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "User không tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        UserDTO userDTO = user.toUserDTO();

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserCreReqDTO userCreReqDTO, BindingResult bindingResult) {
        new UserCreReqDTO().validate(userCreReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Map<String, String> data = new HashMap<>();

        String email = userCreReqDTO.getEmail().trim();
        String username = userCreReqDTO.getUsername().trim();

        if (userService.existsByEmail(email)) {
            data.put("message", "Email đã tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUsername(username)) {
            data.put("message", "Username đã tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        UserCreResDTO userCreResDTO = userService.create(userCreReqDTO);

        return new ResponseEntity<>(userCreResDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> edit(@PathVariable("userId") Long userId,
                                  @RequestBody UserUpReqDTO userUpReqDTO,
                                  BindingResult bindingResult) {

        new UserUpReqDTO().validate(userUpReqDTO, bindingResult);

        Optional<User> userOptional = userService.findById(userId);

        if (userOptional.isEmpty()) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã user không tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Map<String, String> data = new HashMap<>();

        String email = userUpReqDTO.getEmail().trim();
        String username = userUpReqDTO.getUsername().trim();

        if (userService.existsByEmailAndIdNot(email, userId)) {
            data.put("message", "Email user đã tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByUsernameAndIdNot(username, userId)) {
            data.put("message", "Username đã tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        UserUpResDTO userUpResDTO = userService.update(userId, userUpReqDTO);

        return new ResponseEntity<>(userUpResDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Long userId) {
        try {
            Optional<User> userOptional = userService.findById(userId);

            if (userOptional.isEmpty()) {
                Map<String, String> data = new HashMap<>();
                data.put("message", "User không tồn tại");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }
            User user = userOptional.get();
            user.setDeleted(true);
            userService.save(user);
            List<User> users = userService.findAllByDeletedIs(false);

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> data = new HashMap<>();
            data.put("message", "Lỗi xóa user");
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
