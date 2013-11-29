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
import wcworkshop.core.repo.Wc1MedalRepo;
import wcworkshop.core.repo.Wc1PilotRepo;

@Controller
public class MissionEditorController {

  private Wc1GameDataReader gameDataReader = Wc1GameDataReader.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private Wc1PilotRepo pilotRepo = Wc1PilotRepo.getInstance();
  private Wc1MedalRepo medalRepo = Wc1MedalRepo.getInstance();

  @RequestMapping("/missionEditor.html")
  public String renderEditor(@RequestParam String campaign, @RequestParam int seriesIndex, @RequestParam int missionIndex, Model model) {

    Wc1GameData gameData = gameDataReader.readData(campaign);
    Wc1SeriesSlot seriesSlot = gameData.getSeriesSlots().get(seriesIndex);
    Wc1MissionSlot missionSlot = seriesSlot.getMissionSlot(missionIndex);

    model.addAttribute("campaign", campaign);
    model.addAttribute("seriesIndex", seriesIndex);
    model.addAttribute("missionIndex", missionIndex);
    model.addAttribute("mission", missionSlot);
    model.addAttribute("pilotRepo", pilotRepo);
    model.addAttribute("medalRepo", medalRepo);
    model.addAttribute("objectiveVictoryPoints", readerHelper.byteArrayToHexString(missionSlot.getObjectiveVictoryPoints()));

    return "editors/missionEditor";
  }
}
