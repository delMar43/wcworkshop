package wcworkshop.web.controller.editor;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.service.ProjectService;
import wcworkshop.core.service.Wc1CutsceneType;
import wcworkshop.web.command.CutsceneCommand;

@Controller
public class CutsceneEditorPostController {
  private static final Logger logger = LoggerFactory.getLogger(CutsceneEditorPostController.class);
  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping(value = "/saveCutscene", method = RequestMethod.POST)
  public String saveCutscene(@ModelAttribute("command") CutsceneCommand command) {
    String username = (String) SecurityUtils.getSubject().getPrincipal();
    logger.info(username + " saves cutscene");

    Wc1Cutscene cutscene = new Wc1Cutscene();
    cutscene.setScreens(command.getScreens());
    Wc1CutsceneType cutsceneType = Wc1CutsceneType.valueOf(command.getCutsceneType());
    projectService.updateCutscene(username, command.getProjectId(), command.getMissionId(), cutscene, cutsceneType);

    return "forward:/cutsceneEditor.html";
  }
}
