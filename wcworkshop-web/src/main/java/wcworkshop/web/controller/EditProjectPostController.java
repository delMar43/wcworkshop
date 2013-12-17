package wcworkshop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wcworkshop.web.command.ProjectCommand;

@Controller
public class EditProjectPostController {

  private static final Logger logger = LoggerFactory.getLogger(EditProjectPostController.class);

  @RequestMapping(value = "/editProject", method = RequestMethod.POST)
  public void createProject(@ModelAttribute ProjectCommand command) {
    logger.info("createProject called");
  }
}
