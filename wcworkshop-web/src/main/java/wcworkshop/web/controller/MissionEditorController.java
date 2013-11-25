package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.data.Wc1MissionSlot;
import wcworkshop.core.data.Wc1SeriesSlot;
import wcworkshop.core.reader.ReaderHelper;
import wcworkshop.core.reader.Wc1GameDataReader;
import wcworkshop.core.util.Wc1CampUtil;

@Controller
public class MissionEditorController {

  private Wc1GameDataReader gameDataReader = Wc1GameDataReader.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  @RequestMapping("/missionEditor.html")
  public String renderEditor(@RequestParam int seriesIndex, @RequestParam int missionIndex, Model model) {

    Wc1GameData gameData = gameDataReader.readData();
    Wc1SeriesSlot seriesSlot = gameData.getSeriesSlots().get(seriesIndex);
    Wc1MissionSlot missionSlot = seriesSlot.getMissionSlot(missionIndex);

    model.addAttribute("mission", missionSlot);
    model.addAttribute("campUtil", new Wc1CampUtil());
    model.addAttribute("objectiveVictoryPoints", readerHelper.byteArrayToHexString(missionSlot.getObjectiveVictoryPoints()));

    return "editors/missionEditor";
  }
}
