package wcworkshop.web.controller.editor;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.dto.Wc1CutsceneBackground;
import wcworkshop.core.dto.Wc1CutsceneForeground;
import wcworkshop.core.dto.Wc1CutsceneTextColor;
import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.service.ProjectService;
import wcworkshop.core.service.Wc1CutsceneType;
import wcworkshop.web.command.CutsceneCommand;

@Controller
public class CutsceneEditorFormController {

  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping("/cutsceneEditor")
  public String render(@RequestParam String projectId, @RequestParam String missionId, @RequestParam String cutsceneType, Model model) {

    String username = (String) SecurityUtils.getSubject().getPrincipal();
    Wc1Mission mission = projectService.loadMission(username, projectId, missionId);

    Map<Wc1CutsceneType, Wc1Cutscene> cutscenes = mission.getCutscenes();
    Wc1CutsceneType cutsceneTypeEnum = Wc1CutsceneType.valueOf(cutsceneType.toUpperCase());
    Wc1Cutscene cutscene = cutscenes.get(cutsceneTypeEnum);

    CutsceneCommand command = new CutsceneCommand();
    command.setProjectId(projectId);
    command.setMissionId(missionId);
    command.setCutsceneType(cutsceneTypeEnum.toString());
    command.setScreens(cutscene.getScreens());

    model.addAttribute("missionId", missionId);
    model.addAttribute("cutsceneType", cutsceneTypeEnum);
    model.addAttribute("projectId", projectId);
    model.addAttribute("command", command);
    model.addAttribute("foregrounds", Wc1CutsceneForeground.values());
    model.addAttribute("backgrounds", Wc1CutsceneBackground.values());
    model.addAttribute("textColors", Wc1CutsceneTextColor.values());
    model.addAttribute("cutsceneId", projectId + "_" + missionId + "_" + cutsceneType);

    return "editors/cutsceneEditor";
  }
}
