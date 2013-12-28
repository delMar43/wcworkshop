package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionNode extends AbstractNode {

  private List<NavPointNode> navPointNodes = new ArrayList<>();

  public MissionNode(String id, String label) {
    super(id, label, true);
  }

  public void addNavPointNode(NavPointNode node) {
    navPointNodes.add(node);
  }

  @Override
  public Collection<AbstractNode> getChildren() {
    List<AbstractNode> result = new ArrayList<>();

    result.add(new CutscenesNode("abc"));

    NavPointsNode npn = new NavPointsNode("abc");
    npn.setNavPointNodes(navPointNodes);
    result.add(npn);

    return result;
  };

  public List<NavPointNode> getNavPointNodes() {
    return navPointNodes;
  }
}
