package wcworkshop.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @RequestMapping(value = "/login")
  public ModelAndView renderLoginForm() {
    ModelAndView result = new ModelAndView("login");

    Subject subject = SecurityUtils.getSubject();
    result.addObject("isRemembered", subject.isRemembered());
    result.addObject("isAuthenticated", subject.isAuthenticated());

    return result;
  }

}
