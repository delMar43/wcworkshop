package wcworkshop.core.model.tree;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectNode extends AbstractNode {

  private Map<String, SeriesNode> seriesNodes = new LinkedHashMap<>();

  public ProjectNode(String label) {
    super(label);
  }

  public void addSeriesNode(String id, SeriesNode seriesNode) {
    seriesNodes.put(id, seriesNode);
  }

}
