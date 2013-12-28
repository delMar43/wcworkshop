package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CutscenesNode extends AbstractNode {

  public CutscenesNode(String id) {
    super(id, "Cutscenes", true);
  }

  @Override
  public Collection<? extends AbstractNode> getChildren() {
    List<CutsceneNode> children = new ArrayList<>();

    children.add(new CutsceneNode("1", "Briefing"));
    children.add(new CutsceneNode("2", "Debriefing"));
    children.add(new CutsceneNode("3", "Shotglass"));
    children.add(new CutsceneNode("4", "Left"));
    children.add(new CutsceneNode("5", "Right"));

    return children;
  }
}
