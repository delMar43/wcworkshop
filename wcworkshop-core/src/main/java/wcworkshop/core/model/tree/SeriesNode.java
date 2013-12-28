package wcworkshop.core.model.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SeriesNode extends AbstractNode {

  private Map<String, MissionNode> missionNodes = new LinkedHashMap<>();

  public SeriesNode(String id, String label, Map<String, String> data) {
    super(id, label, true, data);
  }

  public void addMissionNode(String id, MissionNode node) {
    missionNodes.put(id, node);
  }

  @Override
  public Collection<MissionNode> getChildren() {
    return Collections.unmodifiableCollection(missionNodes.values());
  }
}
