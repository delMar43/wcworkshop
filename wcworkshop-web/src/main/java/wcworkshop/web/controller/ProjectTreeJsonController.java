package wcworkshop.web.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wcworkshop.core.model.tree.ProjectNodeData;
import wcworkshop.web.factory.UiFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProjectTreeJsonController {
  private UiFactory uiFactory = UiFactory.getInstance();
  private static final ObjectMapper mapper = new ObjectMapper();

  @ResponseBody
  @RequestMapping("/projectTree.json")
  public String renderTree(Model model) {
    Subject subject = SecurityUtils.getSubject();

    String username = (String) subject.getPrincipal();
    List<ProjectNodeData> projectNodes = uiFactory.loadProjectTree(username);

    String json;
    try {
      json = mapper.writeValueAsString(projectNodes);
    } catch (JsonProcessingException e) {
      json = "[]";
    }
    return json;
  }

  private boolean isLastNode(int curNode, int nrNodes) {
    return curNode >= nrNodes - 1;
  }
}
