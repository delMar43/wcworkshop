package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wcworkshop.web.command.CutsceneCommand;

@Controller
public class CutsceneEditorPostController {

  @RequestMapping(value = "/saveCutscene", method = RequestMethod.POST)
  public String saveCutscene(@ModelAttribute("command") CutsceneCommand command) {

    return "forward:/cutsceneEditor";
  }
}
