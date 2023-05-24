package be.eafcuccle.projint.debugger2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    public static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/")
    public String showHome() {
        logger.debug("Showing home page");
        return "redirect:/register-form";
    }

    @GetMapping("/register-form")
    public String showRegistrationForm(@RequestParam(required = false) String error, Model model) {
        logger.debug("Showing registration form");
        model.addAttribute("registrationForm", new RegistrationForm());
        model.addAttribute("error", error);
        return "register-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegistrationForm registrationForm, Model model) {
        registrationForm.validate();
        model.addAttribute("registrationForm", registrationForm);
        if (registrationForm.hasErrors()) {
            logger.debug("Registration form has no error");
            logger.info("User {} registered successfully", registrationForm.getUsername());
            return "register-success";
        } else {
            logger.debug("Registration form has errors");
            return "register-form";
        }
    }
}
