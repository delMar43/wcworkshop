package wcworkshop.web.controller.editor;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.SeriesCommand;

@Controller
public class SeriesEditorPostController {

  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping(value = "saveSeries", method = RequestMethod.POST)
  public ModelAndView saveSeries(@ModelAttribute("command") SeriesCommand command) {
    Wc1Series series = command.getSeries();
    String seriesId = series.getId();

    String username = (String) SecurityUtils.getSubject().getPrincipal();

    projectService.updateSeries(username, command.getProjectId(), series);

    ModelAndView result = new ModelAndView("forward:/seriesEditor.html?seriesId=" + seriesId);
    return result;
  }
}
