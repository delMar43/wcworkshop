package wcworkshop.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.dto.Project;
import wcworkshop.core.model.tree.ProjectTreeModelFactory;
import wcworkshop.core.reader.Wc1GameDataReader;
import wcworkshop.core.service.ProjectService;

@Controller
public class ProjectTreeController {
  private Wc1GameDataReader dataReader = Wc1GameDataReader.getInstance();
  private ProjectService projectService = ProjectService.getInstance();
  private ProjectTreeModelFactory projectTreeFactory = ProjectTreeModelFactory.getInstance();

  @RequestMapping("/projectTree")
  public String renderTree(Model model, @RequestParam(value = "campaign") String campaignId) {

    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();

    String username = (String) subject.getPrincipal();
    Project project = projectService.loadProject(username, campaignId);

    model.addAttribute("project", project);
    model.addAttribute("campaign", campaignId);

    return "campaignContentsTree";
  }
}
