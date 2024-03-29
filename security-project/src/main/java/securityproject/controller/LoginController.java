package securityproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import securityproject.model.user.MyUserDetails;
import securityproject.model.user.Role;
import securityproject.service.AlarmService;
import securityproject.service.UserService;
import securityproject.util.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    UserService userService;
    @Autowired
    AlarmService alarmService;

    private class ResponseBody {
        public String jwt;
        public String cookie;
    }

    @GetMapping(value="login-success/{email}")
    public ResponseEntity<ResponseBody> createToken(@PathVariable String email) {
        MyUserDetails u = (MyUserDetails) userService.loadUserByUsername(email);
        Role r = (Role) u.getUser().getRoles().toArray()[0];
        String fingerprint = tokenUtils.generateFingerprint();
        String jwt = tokenUtils.generateToken(email, r.getName(),fingerprint);
        // Kreiraj cookie
        // String cookie = "__Secure-Fgp=" + fingerprint + "; SameSite=Strict; HttpOnly; Path=/; Secure";  // kasnije mozete probati da postavite i ostale atribute, ali tek nakon sto podesite https
        String cookie = "Fingerprint=" + fingerprint + "; HttpOnly; Path=/";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", jwt);
        responseHeaders.add("Set-Cookie", cookie);

        ResponseBody body = new ResponseBody();
        body.jwt = jwt;
        body.cookie = cookie;
        return ResponseEntity.ok().headers(responseHeaders).body(body);
    }

    @GetMapping(value="login-failure")
    public ResponseEntity<Map<String, Object>> handleLoginFailure(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        AuthenticationException exception = (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        response.put("error", true);

        if (exception != null){
            response.put("message", exception.getMessage());
        } else {
            response.put("message", "null");
        }

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
