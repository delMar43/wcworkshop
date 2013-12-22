package wcworkshop.web.controller.project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wcworkshop.core.dto.EngineType;
import wcworkshop.core.dto.Project;
import wcworkshop.core.dto.Wc1Campaign;
import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.ProjectCommand;

@Controller
public class EditProjectPostController {
  private static final Logger logger = LoggerFactory.getLogger(EditProjectPostController.class);

  private ProjectService projectService = ProjectService.getInstance();

  @ResponseBody
  @RequestMapping(value = "/editProject", method = RequestMethod.POST)
  public String createProject(@ModelAttribute ProjectCommand command) {
    logger.info("createProject called");

    Project project = commandToProject(command);

    projectService.saveProject(project);

    return "{\"success\":true,\"projectId\":\"" + project.getTitle() + "\"}";
  }

  private Project commandToProject(ProjectCommand command) {
    Project project = new Project();
    String projectId;
    if (command.getId() == null || command.getId().length() == 0) {
      projectId = UUID.randomUUID().toString();
    } else {
      projectId = command.getId();
    }
    project.setId(projectId);

    Subject subject = SecurityUtils.getSubject();
    String username = (String) subject.getPrincipal();

    project.setOwner(username);
    project.setTitle(command.getTitle());
    project.setDescriptions(command.getDescriptions());
    project.setWebsite(command.getWebsite());
    project.setEngineType(EngineType.WC1);

    Wc1Campaign campaign = new Wc1Campaign();
    project.setCampaign(campaign);

    List<Wc1Series> seriesList = new ArrayList<>();
    Wc1Series series = new Wc1Series();
    series.setId("Series 1");
    seriesList.add(series);

    campaign.setSeries(seriesList);

    return project;
  }

}
