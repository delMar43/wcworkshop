package wcworkshop.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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

    File usersFile = new File(Configuration.getInstance().getResourcePath() + "/data/security.properties");

    String result;

    try {
      List<String> lines = FileUtils.readLines(usersFile);
      for (String line : lines) {
        if (StringUtils.isBlank(line) || !line.startsWith("user.")) {
          continue;
        }
        line = line.replaceFirst("user.", "");
        line = line.substring(0, line.indexOf("=") - 1);
        if (line.equals(signupCommand.getUsername())) {
          return "redirect:/signup.html";
        }
      }
    } catch (IOException e1) {
      logger.error("Exception while trying to read registration properties file: {}", e1.getMessage());
      return "redirect:/signup.html";
    }

    if (!signupCommand.getPassword().equals(signupCommand.getPasswordConfirm())) {
      result = "redirect:/signup.html";
    } else {

      String line = "user." + signupCommand.getUsername() + " = " + signupCommand.getPassword() + ", user";
      try {
        FileUtils.writeLines(usersFile, Collections.singletonList(line), true);
      } catch (IOException e) {
        logger.error("Exception while trying to write registration to properties file: {}", e.getMessage());
      }

      ProjectService.getInstance().importProjectFromBinaries(signupCommand.getUsername());

      result = "redirect:/login.html";
    }

    return result;
  }
}
