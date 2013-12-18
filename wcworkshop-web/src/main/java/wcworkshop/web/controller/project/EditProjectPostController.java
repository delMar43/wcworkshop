package wcworkshop.web.controller.project;

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

    Subject subject = SecurityUtils.getSubject();
    String username = (String) subject.getPrincipal();

    project.setOwner(username);
    project.setTitle(command.getTitle());
    project.setDescriptions(command.getDescriptions());
    project.setWebsite(command.getWebsite());
    project.setEngineType(EngineType.WC1);

    return project;
  }

}
