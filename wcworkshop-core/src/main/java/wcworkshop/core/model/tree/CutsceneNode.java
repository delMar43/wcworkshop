package wcworkshop.core.model.tree;

import java.util.Map;

public class CutsceneNode extends AbstractNode {

  public CutsceneNode(String id, String label, Map<String, String> data) {
    super(id, label, false, data);
  }

}
