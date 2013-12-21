package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.reader.ReaderHelper;
import wcworkshop.core.repo.Wc1AiPilotRepo;
import wcworkshop.core.repo.Wc1MedalRepo;
import wcworkshop.core.repo.Wc1PilotRepo;
import wcworkshop.core.repo.Wc1ShipRepo;

@Controller
public class MissionEditorController {

  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private Wc1PilotRepo pilotRepo = Wc1PilotRepo.getInstance();
  private Wc1MedalRepo medalRepo = Wc1MedalRepo.getInstance();
  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();
  private Wc1AiPilotRepo aiPilotRepo = Wc1AiPilotRepo.getInstance();

  @RequestMapping("/missionEditor.html")
  public String renderEditor(@RequestParam String campaign, @RequestParam int seriesIndex, @RequestParam int missionIndex, Model model) {

    return "editors/missionEditor";
  }
}
