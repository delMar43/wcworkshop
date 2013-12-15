package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.web.command.SignupCommand;

@Controller
public class SignupGetController {

  @RequestMapping(value = "/signup.html", method = RequestMethod.GET)
  public ModelAndView renderForm() {
    ModelAndView result = new ModelAndView("signup");

    result.addObject("command", new SignupCommand());

    return result;
  }

}
