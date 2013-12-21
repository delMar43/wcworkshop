package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.repo.Wc1ShipRepo;

@Controller
public class NavPointEditorController {

  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();

  @RequestMapping("/navPointEditor")
  public String render(@RequestParam String campaign, @RequestParam int seriesIndex, @RequestParam int missionIndex,
      @RequestParam int navPointIndex, Model model) {

    return "editors/navPointEditor";
  }

}
