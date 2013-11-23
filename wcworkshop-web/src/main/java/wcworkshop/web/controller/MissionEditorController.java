package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MissionEditorController {

  @RequestMapping("/missionEditor.html")
  public String renderEditor(@RequestParam int seriesIndex, @RequestParam int missionIndex, Model model) {

    model.addAttribute("series", seriesIndex);
    model.addAttribute("mission", missionIndex);

    return "editors/missionEditor";
  }
}
