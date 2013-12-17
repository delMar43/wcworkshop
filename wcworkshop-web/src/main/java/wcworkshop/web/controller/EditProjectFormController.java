package wcworkshop.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Project;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.command.ProjectCommand;

@Controller
public class EditProjectFormController {
  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping(value = "/projectForm", method = RequestMethod.GET)
  public ModelAndView editOrCreateProject(@RequestParam(required = false) String projectTitle) {
    ModelAndView result = new ModelAndView("editors/projectForm");

    Subject user = SecurityUtils.getSubject();
    String username = (String) user.getPrincipal();

    ProjectCommand command;
    if (StringUtils.hasText(projectTitle)) {
      Project project = projectService.loadProject(username, projectTitle);
      command = createCommand(project);
    } else {
      command = new ProjectCommand();
    }

    result.addObject("command", command);

    return result;
  }

  private ProjectCommand createCommand(Project project) {
    ProjectCommand result = new ProjectCommand();

    result.setTitle(project.getTitle());
    result.setLanguages(project.getLanguages());
    result.setDescriptions(project.getDescriptions());
    result.setWebsite(project.getWebsite());

    return result;
  }
}
