package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.SeriesCommand;

@Controller
public class SeriesEditorPostController {

  private ProjectService projectService = ProjectService.getInstance();

  @ResponseBody
  @RequestMapping(value = "saveSeries", method = RequestMethod.POST)
  public String saveSeries(@ModelAttribute("command") SeriesCommand command) {
    Wc1Series series = command.getSeries();
    String seriesId = series.getId();

    return "{\"status\":\"ok\"}";
  }
}
