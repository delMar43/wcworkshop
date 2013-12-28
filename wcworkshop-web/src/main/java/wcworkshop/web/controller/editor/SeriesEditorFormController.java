package wcworkshop.web.controller.editor;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.model.SeriesModel;
import wcworkshop.core.repo.Wc1PilotRepo;
import wcworkshop.core.repo.Wc1ShipRepo;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.SeriesCommand;

@Controller
public class SeriesEditorFormController {

  private ProjectService projectService = ProjectService.getInstance();
  private Wc1PilotRepo pilotRepo = Wc1PilotRepo.getInstance();
  private Wc1ShipRepo shipRepo = Wc1ShipRepo.getInstance();

  @RequestMapping(value = "/seriesEditor")
  public ModelAndView renderEditor(@RequestParam String projectId, @RequestParam String seriesId) {
    ModelAndView result = new ModelAndView("editors/seriesEditor");

    String username = (String) SecurityUtils.getSubject().getPrincipal();

    Wc1Series series = projectService.loadSeries(username, projectId, seriesId);
    List<SeriesModel> allSeries = projectService.listAllSeries(username, projectId, null);

    SeriesCommand command = new SeriesCommand();
    command.setProjectId(projectId);
    command.setSeries(series);

    result.addObject("command", command);
    result.addObject("pilots", pilotRepo.listAll());
    result.addObject("flyableShips", shipRepo.listAllFlyable());
    result.addObject("allSeries", allSeries);

    return result;
  }
}
