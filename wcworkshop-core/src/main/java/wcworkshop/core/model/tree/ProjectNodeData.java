package wcworkshop.core.model.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectNodeData extends AbstractNode {

  private Map<String, SeriesNodeData> seriesNodes = new LinkedHashMap<>();

  public ProjectNodeData(String id, String label) {
    super(id, label, true);
  }

  public void addSeriesNode(String id, SeriesNodeData seriesNode) {
    seriesNodes.put(id, seriesNode);
  }

  @Override
  public Collection<SeriesNodeData> getChildren() {
    return Collections.unmodifiableCollection(seriesNodes.values());
  }
}
