package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.data.Wc1Cutscene;
import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.reader.Wc1GameDataReader;
import wcworkshop.core.util.Wc1CutsceneUtil;

@Controller
public class CutsceneEditorController {

  private Wc1GameDataReader gameDataReader = Wc1GameDataReader.getInstance();
  private Wc1CutsceneUtil cutsceneUtil = Wc1CutsceneUtil.getInstance();

  @RequestMapping("/cutsceneEditor")
  public String render(@RequestParam int seriesIndex, @RequestParam int missionIndex, @RequestParam int cutsceneIndex, Model model) {

    Wc1GameData gameData = gameDataReader.readData();
    Wc1Cutscene cutscene = gameData.getSeriesSlots().get(seriesIndex).getMissionSlot(missionIndex).getCutscene(cutsceneIndex);

    model.addAttribute("cutscene", cutscene);
    model.addAttribute("cutsceneUtil", cutsceneUtil);

    return "editors/cutsceneEditor";
  }
}
