package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.List;

public class NavPointsNode extends AbstractNode {

  private List<NavPointNode> navPointNodes = new ArrayList<>();

  public NavPointsNode(String id) {
    super(id, "Nav Points", true);
  }

  public void addNavPointNode(NavPointNode node) {
    navPointNodes.add(node);
  }

  @Override
  public List<NavPointNode> getChildren() {
    return navPointNodes;
  }

  public void setNavPointNodes(List<NavPointNode> navPointNodes) {
    this.navPointNodes = navPointNodes;
  }
}
