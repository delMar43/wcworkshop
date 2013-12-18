package wcworkshop.web.controller.editor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.data.Wc1SeriesSlot;
import wcworkshop.core.reader.Wc1GameDataReader;
import wcworkshop.core.repo.Wc1PilotRepo;
import wcworkshop.core.repo.Wc1ShipRepo;

@Controller
public class SeriesEditorController {

  private Wc1GameDataReader gameDataReader = Wc1GameDataReader.getInstance();
  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();
  private Wc1PilotRepo pilotRepo = Wc1PilotRepo.getInstance();

  @RequestMapping("/seriesEditor.html")
  public String renderEditor(@RequestParam String campaign, @RequestParam int seriesIndex, Model model) {

    Wc1GameData gameData = gameDataReader.readData(campaign);
    List<Wc1SeriesSlot> seriesSlots = gameData.getSeriesSlots();
    Wc1SeriesSlot series = seriesSlots.get(seriesIndex);

    model.addAttribute("campaign", campaign);
    model.addAttribute("seriesIndex", seriesIndex);
    model.addAttribute("series", series);
    model.addAttribute("pilotRepo", pilotRepo);
    model.addAttribute("shipRepo", shipRepo);

    return "editors/seriesEditor";
  }
}