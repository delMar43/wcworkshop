package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.data.Wc1GameData;
import wcworkshop.core.reader.Wc1GameDataReader;

@Controller
public class CampaignContentsTreeController {
  private Wc1GameDataReader dataReader = Wc1GameDataReader.getInstance();

  @RequestMapping("/campaignContentsTree")
  public String renderTree(Model model, @RequestParam String campaign) {
    Wc1GameData wc1Data = dataReader.readData(campaign);

    model.addAttribute("gameData", wc1Data);
    model.addAttribute("campaign", campaign);

    return "campaignContentsTree";
  }
}
