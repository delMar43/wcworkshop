package wcworkshop.core.model.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SeriesNodeData extends AbstractNode {

  private Map<String, MissionNodeData> missionNodes = new LinkedHashMap<>();

  public SeriesNodeData(String id, String label, Map<String, String> data) {
    super(id, label, true, data);
  }

  public void addMissionNode(String id, MissionNodeData node) {
    missionNodes.put(id, node);
  }

  @Override
  public Collection<MissionNodeData> getChildren() {
    return Collections.unmodifiableCollection(missionNodes.values());
  }
}
