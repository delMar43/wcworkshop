package wcworkshop.web.controller.editor;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.service.ProjectService;

@Controller
public class SeriesEditorFormController {

  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping(value = "/seriesEditor.html", method = RequestMethod.GET)
  public ModelAndView renderEditor(@RequestParam String campaign, @RequestParam int seriesIndex) {
    ModelAndView result = new ModelAndView("editors/seriesEditor");

    String username = (String) SecurityUtils.getSubject().getPrincipal();

    Project project = projectService.loadProject(username, campaign);
    Wc1Series series = project.getCampaign().getSeries().get(seriesIndex);

    result.addObject("command", series);

    return result;
  }
}
