package wcworkshop.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wcworkshop.core.config.Configuration;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.SignupCommand;

@Controller
public class SignupPostController {

  private static final Logger logger = LoggerFactory.getLogger(SignupPostController.class);

  @RequestMapping(value = "/signup.html", method = RequestMethod.POST)
  public String receiveRequest(@ModelAttribute SignupCommand signupCommand) {
    logger.info("Signup received");

    String result;
    if (!signupCommand.getPassword().equals(signupCommand.getPasswordConfirm())) {
      result = "redirect:/signup.html";
    } else {

      String line = "user." + signupCommand.getUsername() + " = " + signupCommand.getPassword() + ", user";
      try {
        FileUtils.writeLines(new File(Configuration.getInstance().getResourcePath() + "/data/security.properties"),
            Collections.singletonList(line), true);
      } catch (IOException e) {
        logger.error("Exception while trying to write registration to properties file: {}", e.getMessage());
      }

      ProjectService.getInstance().importProjectFromBinaries(signupCommand.getUsername());

      result = "redirect:/login.html";
    }

    return result;
  }
}
