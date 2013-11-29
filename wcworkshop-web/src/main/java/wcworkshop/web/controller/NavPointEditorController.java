package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.data.Wc1NavPoint;
import wcworkshop.core.reader.Wc1GameDataReader;
import wcworkshop.core.repo.Wc1ShipRepo;

@Controller
public class NavPointEditorController {

  private Wc1GameDataReader gameDataReader = Wc1GameDataReader.getInstance();
  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();

  @RequestMapping("/navPointEditor")
  public String render(@RequestParam String campaign, @RequestParam int seriesIndex, @RequestParam int missionIndex,
      @RequestParam int navPointIndex, Model model) {

    Wc1GameData gameData = gameDataReader.readData(campaign);
    Wc1NavPoint navPoint = gameData.getSeriesSlots().get(seriesIndex).getMissionSlot(missionIndex).getNavPoints().get(navPointIndex);

    model.addAttribute("seriesIndex", seriesIndex);
    model.addAttribute("missionIndex", missionIndex);
    model.addAttribute("navPointIndex", navPointIndex);
    model.addAttribute("navPoint", navPoint);
    model.addAttribute("shipRepo", shipRepo);

    return "editors/navPointEditor";
  }

}
