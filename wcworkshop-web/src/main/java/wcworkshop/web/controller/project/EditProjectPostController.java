package wcworkshop.web.controller.project;

import java.util.Collections;

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
import wcworkshop.core.dto.ProjectFactory;
import wcworkshop.core.model.tree.ProjectNode;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.ProjectCommand;
import wcworkshop.web.factory.UiFactory;

@Controller
public class EditProjectPostController {
  private static final Logger logger = LoggerFactory.getLogger(EditProjectPostController.class);

  private ProjectService projectService = ProjectService.getInstance();
  private ProjectFactory projectFactory = ProjectFactory.getInstance();
  private UiFactory uiFactory = UiFactory.getInstance();

  @ResponseBody
  @RequestMapping(value = "/editProject", method = RequestMethod.POST)
  public ProjectNode createProject(@ModelAttribute ProjectCommand command) {
    logger.info("createProject called");

    Project project = commandToProject(command);

    projectService.saveProject(project);

    ProjectNode node = uiFactory.createProjectNode(project);

    return node;
  }

  private Project commandToProject(ProjectCommand command) {
    Subject subject = SecurityUtils.getSubject();
    String username = (String) subject.getPrincipal();

    Project project;
    if (command.getId() == null || command.getId().length() == 0) {
      project = projectFactory.createProject(username, command.getTitle(), Collections.singletonList("en"), command.getDescriptions(),
          command.getWebsite(), EngineType.WC1);
    } else {
      project = projectService.loadProject(username, command.getId());

      project.setOwner(username);
      project.setTitle(command.getTitle());
      project.setDescriptions(command.getDescriptions());
      project.setWebsite(command.getWebsite());
    }

    return project;
  }

}
