package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.repo.Wc1PilotRepo;
import wcworkshop.core.repo.Wc1ShipRepo;

@Controller
public class SeriesEditorController {

  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();
  private Wc1PilotRepo pilotRepo = Wc1PilotRepo.getInstance();

  @RequestMapping("/seriesEditor.html")
  public String renderEditor(@RequestParam String campaign, @RequestParam int seriesIndex, Model model) {

    return "editors/seriesEditor";
  }
}
