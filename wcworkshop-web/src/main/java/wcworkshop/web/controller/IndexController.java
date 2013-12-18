package wcworkshop.web.controller;

import java.util.Collections;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Project;
import wcworkshop.core.service.ProjectService;
import wcworkshop.web.model.UiState;

@Controller
public class IndexController {

  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping("/index")
  public ModelAndView index() {
    ModelAndView result = new ModelAndView("index");

    Subject user = SecurityUtils.getSubject();

    Session session = user.getSession();
    UiState uiState = (UiState) session.getAttribute("uiState");

    List<Project> projects = projectService.listProjects((String) user.getPrincipal());

    List<String> openProjects;
    if (uiState != null) {
      openProjects = uiState.getOpenProjects();
    } else {
      openProjects = Collections.EMPTY_LIST;
    }
    result.addObject("openProjects", projects);

    return result;
  }
}
