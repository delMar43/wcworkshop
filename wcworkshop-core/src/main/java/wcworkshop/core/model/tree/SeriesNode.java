package wcworkshop.core.model.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SeriesNode extends AbstractNode {

  private Map<String, MissionNode> missionNodes = new LinkedHashMap<>();

  public SeriesNode(String id, String label) {
    super(id, label);
  }

  public void addMissionNode(String id, MissionNode node) {
    missionNodes.put(id, node);
  }

  public Collection<MissionNode> getMissionNodes() {
    return Collections.unmodifiableCollection(missionNodes.values());
  }
}
