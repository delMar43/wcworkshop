package wcworkshop.web.controller.editor;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.SeriesCommand;

@Controller
public class SeriesEditorFormController {

  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping(value = "/seriesEditor.html", method = RequestMethod.GET)
  public ModelAndView renderEditor(@RequestParam String projectId, @RequestParam String seriesId) {
    ModelAndView result = new ModelAndView("editors/seriesEditor");

    String username = (String) SecurityUtils.getSubject().getPrincipal();

    Wc1Series series = projectService.loadSeries(username, projectId, seriesId);

    SeriesCommand command = new SeriesCommand();
    command.setProjectId(projectId);
    command.setSeries(series);

    result.addObject("command", command);

    return result;
  }
}
