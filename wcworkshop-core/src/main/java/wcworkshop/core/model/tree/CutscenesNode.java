package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CutscenesNode extends AbstractNode {

  private List<CutsceneNode> children = new ArrayList<>();

  public CutscenesNode(String id, Map<String, String> data) {
    super(id, "Cutscenes", true, data);
  }

  public void addCutsceneNode(CutsceneNode cutsceneNode) {
    children.add(cutsceneNode);
  }

  @Override
  public Collection<? extends AbstractNode> getChildren() {
    return children;
  }
}
