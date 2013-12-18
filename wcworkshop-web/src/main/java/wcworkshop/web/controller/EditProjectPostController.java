package wcworkshop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wcworkshop.web.command.ProjectCommand;

@Controller
public class EditProjectPostController {

  private static final Logger logger = LoggerFactory.getLogger(EditProjectPostController.class);

  @ResponseBody
  @RequestMapping(value = "/editProject", method = RequestMethod.POST)
  public String createProject(@ModelAttribute ProjectCommand command) {
    logger.info("createProject called");

    return "{success:true}";
  }
}
