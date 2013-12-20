package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.service.Wc1CutsceneReadService;
import wcworkshop.core.service.Wc1CutsceneType;
import wcworkshop.core.service.Wc1Cutscenes;
import wcworkshop.core.service.Wc1MissionCutscenes;
import wcworkshop.core.util.Wc1CutsceneUtil;

@Controller
public class CutsceneEditorController {

  private Wc1CutsceneReadService cutsceneService = Wc1CutsceneReadService.getInstance();
  private Wc1CutsceneUtil cutsceneUtil = Wc1CutsceneUtil.getInstance();

  @RequestMapping("/cutsceneEditor")
  public String render(@RequestParam String campaign, @RequestParam int seriesIndex, @RequestParam int missionIndex,
      @RequestParam String cutsceneIndex, Model model) {

    Wc1Cutscenes cutscenes = cutsceneService.loadCutscenes(campaign);
    int absIndex = seriesIndex * 4 + missionIndex;
    Wc1MissionCutscenes wc1MissionCutscenes = cutscenes.getMissionCutscenes().get(absIndex);
    Wc1CutsceneType cutsceneType = Wc1CutsceneType.valueOf(cutsceneIndex);
    Wc1Cutscene cutscene = wc1MissionCutscenes.getCutscenes().get(cutsceneType);

    model.addAttribute("missionIndex", missionIndex);
    model.addAttribute("seriesIndex", seriesIndex);
    model.addAttribute("cutsceneIndex", cutsceneIndex);
    model.addAttribute("campaign", campaign);
    model.addAttribute("cutscene", cutscene);
    model.addAttribute("cutsceneUtil", cutsceneUtil);

    return "editors/cutsceneEditor";
  }
}
