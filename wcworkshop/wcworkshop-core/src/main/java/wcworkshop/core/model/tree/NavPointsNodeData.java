package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.List;

public class NavPointsNodeData extends AbstractNode {

  private List<NavPointNodeData> navPointNodes = new ArrayList<>();

  public NavPointsNodeData(String id) {
    super(id, "Nav Points", true);
  }

  public void addNavPointNode(NavPointNodeData node) {
    navPointNodes.add(node);
  }

  @Override
  public List<NavPointNodeData> getChildren() {
    return navPointNodes;
  }

  public void setNavPointNodes(List<NavPointNodeData> navPointNodes) {
    this.navPointNodes = navPointNodes;
  }
}
