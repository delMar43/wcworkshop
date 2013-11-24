package wcworkshop.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.data.Wc1SeriesSlot;
import wcworkshop.core.reader.Wc1GameDataReader;
import wcworkshop.core.util.Wc1CampPilotUtil;

@Controller
public class SeriesEditorController {

  private Wc1GameDataReader gameDataReader = Wc1GameDataReader.getInstance();

  @RequestMapping("/seriesEditor.html")
  public String renderEditor(@RequestParam int seriesIndex, Model model) {

    Wc1GameData gameData = gameDataReader.readData();
    List<Wc1SeriesSlot> seriesSlots = gameData.getSeriesSlots();
    Wc1SeriesSlot series = seriesSlots.get(seriesIndex);

    model.addAttribute("seriesIndex", seriesIndex);
    model.addAttribute("series", series);
    model.addAttribute("pilots", new Wc1CampPilotUtil());

    return "editors/seriesEditor";
  }
}
