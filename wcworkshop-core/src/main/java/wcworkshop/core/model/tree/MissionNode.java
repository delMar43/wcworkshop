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
  }

  @Override
  public Collection<AbstractNode> getChildren() {
    List<AbstractNode> result = new ArrayList<>();

    result.add(cutscenesNode);
    result.add(navPointsNode);

    return result;
  };

  public void setNavPointsNode(NavPointsNode navPointsNode) {
    this.navPointsNode = navPointsNode;
  }

  public void setCutscenesNode(CutscenesNode cutscenesNode) {
    this.cutscenesNode = cutscenesNode;
  }
}
