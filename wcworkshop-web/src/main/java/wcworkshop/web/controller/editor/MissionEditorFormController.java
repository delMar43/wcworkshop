package wcworkshop.web.controller.editor;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Wc1Mission;
import wcworkshop.core.repo.Wc1AiPilotRepo;
import wcworkshop.core.repo.Wc1MedalRepo;
import wcworkshop.core.repo.Wc1PilotRepo;
import wcworkshop.core.repo.Wc1ShipRepo;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.MissionCommand;

@Controller
public class MissionEditorFormController {

  //  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private Wc1PilotRepo pilotRepo = Wc1PilotRepo.getInstance();
  private Wc1MedalRepo medalRepo = Wc1MedalRepo.getInstance();
  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();
  private Wc1AiPilotRepo aiPilotRepo = Wc1AiPilotRepo.getInstance();
  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping("/missionEditor.html")
  public ModelAndView renderEditor(@RequestParam String projectId, @RequestParam String missionId) {
    ModelAndView result = new ModelAndView("editors/missionEditor");

    String username = (String) SecurityUtils.getSubject().getPrincipal();
    Wc1Mission mission = projectService.loadMission(username, projectId, missionId);

    MissionCommand command = new MissionCommand(projectId, mission);
    result.addObject("command", command);
    result.addObject("projectId", projectId);
    result.addObject("pilots", pilotRepo.listAll());
    result.addObject("medals", medalRepo.listAll());
    result.addObject("pilotRepo", pilotRepo);
    result.addObject("medalRepo", medalRepo);
    result.addObject("shipRepo", shipRepo);
    result.addObject("aiPilotRepo", aiPilotRepo);

    return result;
  }
}
