package pl.endproject.offerscomparator.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.endproject.offerscomparator.infrastructure.userRegistration.model.User;
import pl.endproject.offerscomparator.infrastructure.userRegistration.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/signup")
    public String showSignUpForm(HttpServletResponse response, Model model, @RequestParam(value = "token", required = false, defaultValue = "") String token) throws IOException {
        if (StringUtils.isNotEmpty(token)) {
            if (userService.activateUser(token)) {
                response.getWriter().println("Thank you for activating your account!");
                response.setStatus(200);
                return "white-page";
            } else {
                response.getWriter().println("Something went wrong :(");
                response.setStatus(400);
                return "white-page";
            }
        }


        return "add-user";
    }

    @PostMapping("/signup")
    public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String path = String.valueOf(request.getRequestURL());

        if (!userService.registerUser(login, email, password, path)) {
            model.addAttribute("failure", userService.getFailureCause());
            response.setStatus(409);
            return "add-user";
        }

        response.getWriter().println("Look for the verification email in your inbox and click the link in that email. <br>A confirmation message will appear in your web browser.");
        response.setStatus(200);
        return "white-page";
    }


    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password) {

        User loginUser = userService.loginUser(username, password);
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
        }
        return "redirect:/offers";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loginUser",null);
        return "redirect:/offers";
    }

}
