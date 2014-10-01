package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CutscenesNodeData extends AbstractNode {

  private List<CutsceneNodeData> children = new ArrayList<>();

  public CutscenesNodeData(String id, Map<String, String> data) {
    super(id, "Cutscenes", true, data);
  }

  public void addCutsceneNode(CutsceneNodeData cutsceneNode) {
    children.add(cutsceneNode);
  }

  @Override
  public Collection<? extends AbstractNode> getChildren() {
    return children;
  }
}
