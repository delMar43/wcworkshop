package wcworkshop.web.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wcworkshop.core.dto.Project;
import wcworkshop.core.reader.Wc1GameDataReader;
import wcworkshop.core.service.ProjectService;

@Controller
public class CampaignContentsTreeController {
  private Wc1GameDataReader dataReader = Wc1GameDataReader.getInstance();
  private ProjectService projectService = ProjectService.getInstance();

  @RequestMapping("/campaignContentsTree")
  public String renderTree(Model model, @RequestParam String campaign) {

    String username = (String) SecurityUtils.getSubject().getPrincipal();
    Project project = projectService.loadProject(username, campaign);

    model.addAttribute("project", project);
    model.addAttribute("campaign", campaign);

    return "campaignContentsTree";
  }
}
