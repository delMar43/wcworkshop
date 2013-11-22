package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.reader.Wc1GameDataReader;

@Controller
public class CampaignContentsTreeController {
  private Wc1GameDataReader dataReader = Wc1GameDataReader.getInstance();

  @RequestMapping("/campaignContentsTree")
  public String renderTree(Model model) {
    Wc1GameData wc1Data = dataReader.readData();

    model.addAttribute("gameData", wc1Data);

    return "campaignContentsTree";
  }
}
