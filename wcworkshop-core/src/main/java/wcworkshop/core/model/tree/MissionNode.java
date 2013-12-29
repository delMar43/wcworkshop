package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MissionNode extends AbstractNode {

  private NavPointsNode navPointsNode;
  private CutscenesNode cutscenesNode;

  public MissionNode(String id, String label, Map<String, String> data) {
    super(id, label, true, data);
    navPointsNode = new NavPointsNode(id + "_nav");
    cutscenesNode = new CutscenesNode(id + "_cut");
  }

  @Override
  public Collection<AbstractNode> getChildren() {
    List<AbstractNode> result = new ArrayList<>();

    result.add(cutscenesNode);
    result.add(navPointsNode);

    return result;
  };

  public NavPointsNode getNavPointsNode() {
    return navPointsNode;
  }

  public CutscenesNode getCutscenesNode() {
    return cutscenesNode;
  }
}
