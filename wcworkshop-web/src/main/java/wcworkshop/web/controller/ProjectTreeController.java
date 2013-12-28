package wcworkshop.web.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wcworkshop.core.factory.UiFactory;
import wcworkshop.core.model.tree.ProjectNode;

@Controller
public class ProjectTreeController {
  private UiFactory uiFactory = UiFactory.getInstance();

  @ResponseBody
  @RequestMapping("/projectTree")
  public String renderTree(Model model) {
    Subject subject = SecurityUtils.getSubject();

    String username = (String) subject.getPrincipal();
    List<ProjectNode> projectNodes = uiFactory.loadProjectTree(username);

    StringBuilder result = new StringBuilder("[");
    int nrNodes = projectNodes.size();
    for (int nodeIdx = 0; nodeIdx < nrNodes; ++nodeIdx) {
      ProjectNode node = projectNodes.get(nodeIdx);
      result.append(node.toFancyJson());

      if (!isLastNode(nodeIdx, nrNodes)) {
        result.append(",");
      }
    }
    result.append("]");

    return result.toString();
  }

  private boolean isLastNode(int curNode, int nrNodes) {
    return curNode >= nrNodes - 1;
  }
}
