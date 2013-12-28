package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.web.command.MissionCommand;

@Controller
public class MissionEditorPostController {

  @RequestMapping(value = "/saveMission", method = RequestMethod.POST)
  public ModelAndView save(@ModelAttribute MissionCommand command) {
    ModelAndView result = new ModelAndView("forward:/missionEditor.html?missionId=" + command.getMission().getId());

    return result;
  }
}
