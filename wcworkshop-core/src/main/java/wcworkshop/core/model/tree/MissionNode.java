package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.List;

public class MissionNode extends AbstractNode {

  private List<NavPointNode> navPointNodes = new ArrayList<>();

  public MissionNode(String id, String label) {
    super(id, label);
  }

  public void addNavPointNode(NavPointNode node) {
    navPointNodes.add(node);
  }

  public List<NavPointNode> getNavPointNodes() {
    return navPointNodes;
  }
}
