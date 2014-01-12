package wcworkshop.core.model.tree;

import java.util.Map;

@SuppressWarnings("serial")
public class CutsceneNodeData extends AbstractNode {

  public CutsceneNodeData(String id, String label, String tabLabel, Map<String, String> data) {
    super(id, label, false, data);
    getData().put("tabLabel", tabLabel);
  }

}
