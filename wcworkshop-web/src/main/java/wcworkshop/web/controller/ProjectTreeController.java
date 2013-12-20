package wcworkshop.web.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wcworkshop.core.factory.UiFactory;
import wcworkshop.core.model.tree.ProjectNode;

@Controller
public class ProjectTreeController {
  private UiFactory uiFactory = UiFactory.getInstance();

  @RequestMapping("/projectTree")
  public String renderTree(Model model) {
    Subject subject = SecurityUtils.getSubject();

    String username = (String) subject.getPrincipal();
    List<ProjectNode> projectNodes = uiFactory.loadProjectTree(username);

    model.addAttribute("projectNodes", projectNodes);

    return "projectTree";
  }
}
