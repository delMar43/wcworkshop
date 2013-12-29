package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavPointNode extends AbstractNode {

  private List<NavPointNode> navPointNodes = new ArrayList<>();

  public NavPointNode(String id, String label, Map<String, String> data) {
    super(id, label, false, data);
  }

  public void addNavPointNode(NavPointNode node) {
    navPointNodes.add(node);
  }

  public List<NavPointNode> getNavPointNodes() {
    return navPointNodes;
  }
}
