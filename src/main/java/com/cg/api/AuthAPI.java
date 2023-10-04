package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.JwtResponse;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.model.dto.user.UserRegisterReqDTO;
import com.cg.repository.ConfirmationTokenRepository;
import com.cg.repository.UserRepository;
import com.cg.service.EmailService;
import com.cg.service.jwt.JwtService;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import com.cg.ultis.AppUtils;
import com.cg.ultis.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {
    @Autowired
    private Environment env;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IRoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User u = userRepository.getByUsername(user.getUsername());
        if (!u.isEnabled()) {
            throw new DataInputException("Tài khoản chưa xác thực");
        }
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.getByUsername(user.getUsername());

            JwtResponse jwtResponse = new JwtResponse(
                    jwt,
                    currentUser.getId(),
                    userDetails.getUsername(),
                    currentUser.getUsername(),
                    userDetails.getAuthorities()
            );

            ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(1000L * 60 * 60 * 24 * 30)
                    .domain("localhost")
                    .build();

            System.out.println(jwtResponse);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body(jwtResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }



//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterReqDTO userRegisterReqDTO, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors())
//            return appUtils.mapErrorToResponse(bindingResult);
//
//        Boolean existsByUsername = userService.existsByUsername(userRegisterReqDTO.getUsername());
//
//        if (existsByUsername) {
//            throw new EmailExistsException("Account already exists");
//        }
//
//
//        try {
//            Long roleId = (Long) 2L;
//            Role role = roleService.findById(roleId).get();
//            User user = userRegisterReqDTO.toUser(role);
//            user.setId(null);
//            user.setUsername(userRegisterReqDTO.getUsername());
//            user.setPassword(userRegisterReqDTO.getPassword());
//            user.setFullName("Nhật");
//            user.setEmail("nhat@gmail.com");
//            user.setPhone("123123");
//            user.setAddress("28 NTP");
//            user.setRole(role);
//            userService.save(user);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//
//        } catch (DataIntegrityViolationException e) {
//            throw new DataInputException("Account information is not valid, please check the information again");
//        }
//    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterReqDTO userRegisterReqDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        Boolean existsByUsername = userService.existsByUsername(userRegisterReqDTO.getUsername());
        Boolean existsByEmail = userService.existsByUsername(userRegisterReqDTO.getEmail());

        if (existsByUsername) {
            throw new EmailExistsException("Username already exists");
        }
        if (existsByEmail) {
            throw new EmailExistsException("Email already exists");
        }


        try {
            Long roleId = 2L;
            Role role = roleService.findById(roleId).orElseThrow(() ->
                    new DataInputException("Role không tồn tại"));
            User user = userRegisterReqDTO.toUser(role);
            user.setId(null);
            user.setUsername(userRegisterReqDTO.getUsername());
            user.setPassword(userRegisterReqDTO.getPassword());
            user.setFullName(userRegisterReqDTO.getFullName());
            user.setEmail(userRegisterReqDTO.getEmail());
            user.setPhone(userRegisterReqDTO.getPhone());
            user.setAddress(userRegisterReqDTO.getAddress());
            user.setRole(role);
            userService.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:28002/api/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
            return new ResponseEntity<>(user.toUserCreResDTO(), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again");
        }
    }
    @GetMapping("/confirm-account")
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("confirmPage");

        }
        else
        {
//            modelAndView.setViewName("error");
//            modelAndView.addObject("message","The link is invalid or broken!");
            throw new DataInputException("The link is invalid or broken!");

        }

        return modelAndView;
    }
}
