package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NavPointNodeData extends AbstractNode {

  private List<NavPointNodeData> navPointNodes = new ArrayList<>();

  public NavPointNodeData(String id, String label, Map<String, String> data) {
    super(id, label, false, data);
  }

  public void addNavPointNode(NavPointNodeData node) {
    navPointNodes.add(node);
  }

  public List<NavPointNodeData> getNavPointNodes() {
    return navPointNodes;
  }
}
