package wcworkshop.web.controller.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.dto.Wc1CutsceneScreen;
import wcworkshop.core.service.ProjectService;
import wcworkshop.core.service.Wc1CutsceneType;
import wcworkshop.web.command.CutsceneCommand;

@Controller
public class CutsceneEditorPostController {
  private static final Logger logger = LoggerFactory.getLogger(CutsceneEditorPostController.class);
  private ProjectService projectService = ProjectService.getInstance();
  private static final Comparator<Wc1CutsceneScreen> seqComparator = new Comparator<Wc1CutsceneScreen>() {
    @Override
    public int compare(Wc1CutsceneScreen o1, Wc1CutsceneScreen o2) {
      return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
    }
  };

  @RequestMapping(value = "/saveCutscene", method = RequestMethod.POST)
  public String saveCutscene(@ModelAttribute("command") CutsceneCommand command) {
    String username = (String) SecurityUtils.getSubject().getPrincipal();
    logger.info(username + " saves cutscene");

    List<Wc1CutsceneScreen> screens = command.getScreens();
    Collections.sort(screens, seqComparator);

    List<Wc1CutsceneScreen> newScreens = new ArrayList<>();
    int sequence = 1;
    for (Wc1CutsceneScreen screen : screens) {
      if (screen.getSequence() <= 0) {
        continue;
      }
      screen.setSequence(sequence++);
      newScreens.add(screen);
    }

    Wc1Cutscene cutscene = new Wc1Cutscene();
    cutscene.setScreens(newScreens);

    Wc1CutsceneType cutsceneType = Wc1CutsceneType.valueOf(command.getCutsceneType());
    projectService.updateCutscene(username, command.getProjectId(), command.getMissionId(), cutscene, cutsceneType);

    return "forward:/cutsceneEditor.html";
  }
}
