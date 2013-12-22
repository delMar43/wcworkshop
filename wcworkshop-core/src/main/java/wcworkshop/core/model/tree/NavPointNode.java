package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.List;

public class NavPointNode extends AbstractNode {

  private List<NavPointNode> navPointNodes = new ArrayList<>();

  public NavPointNode(String id, String label) {
    super(id, label);
  }

  public void addNavPointNode(NavPointNode node) {
    navPointNodes.add(node);
  }

  public List<NavPointNode> getNavPointNodes() {
    return navPointNodes;
  }
}
