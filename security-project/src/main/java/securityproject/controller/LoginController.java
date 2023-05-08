package securityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import securityproject.model.user.MyUserDetails;
import securityproject.model.user.Role;
import securityproject.model.user.User;
import securityproject.service.UserService;
import securityproject.util.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    UserService userService;

    @GetMapping(value="login-success")
    public ResponseEntity<String> success(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        MyUserDetails u = (MyUserDetails) userService.loadUserByUsername(username);
        Role r = (Role) u.getUser().getRoles().toArray()[0];
        String token = tokenUtils.generateToken(username, r.getName());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return ResponseEntity.ok().headers(responseHeaders).body("logged in");
    }

    @GetMapping(value="login-failure")
    public ResponseEntity<Map<String, Object>> handleLoginFailure(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        AuthenticationException exception = (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        response.put("error", true);
        response.put("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @GetMapping(value="login-failed")
    public ResponseEntity<Map<String, Object>> handleInvalidPin() {
        Map<String, Object> response = new HashMap<>();

        response.put("error", true);
        response.put("message", "Invalid credentials");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}