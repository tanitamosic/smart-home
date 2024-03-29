package securityproject.security.behaviors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import securityproject.model.user.MyUserDetails;
import securityproject.model.user.User;
import securityproject.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        MyUserDetails userDetails =  (MyUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        if (user.getFailedAttempt() > 0) {
            userService.resetFailedAttempts(user.getEmail());
        }

        Map<String, String[]> paramMap = new HashMap<>(request.getParameterMap());
        Integer pin = Integer.valueOf(paramMap.get("pin")[0]);
        if (!pin.equals(user.getPin())) {
            if (userDetails.getUser().getFailedAttempt() < UserService.MAX_FAILED_ATTEMPTS - 1) {
                userService.increaseFailedAttempts(userDetails.getUser());
            } else {
                userService.lock(userDetails.getUser());
            }
            super.setDefaultTargetUrl("/login-failed");
            super.onAuthenticationSuccess(request, response, authentication);

        } else {
            super.setDefaultTargetUrl("/login-success/".concat(user.getEmail()));
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }

}
