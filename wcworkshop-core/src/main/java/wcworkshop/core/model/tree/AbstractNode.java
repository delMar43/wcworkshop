package wcworkshop.core.model.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractNode {

  private String id;
  private String label;
  private boolean folder;

  public AbstractNode(String id, String label, boolean folder) {
    this.id = id;
    this.label = label;
    this.folder = folder;
  }

  public String toFancyJson() {
    StringBuilder result = new StringBuilder("{");

    result.append(create("title", label));
    result.append(",");
    result.append(create("key", id));
    if (folder) {
      result.append(",");
      result.append(createBoolean("folder", folder));

      result.append(",");
      result.append("\"children\":[");
      List<AbstractNode> children = new ArrayList<>(getChildren());
      int nrNodes = children.size();
      for (int nodeIdx = 0; nodeIdx < nrNodes; ++nodeIdx) {
        AbstractNode node = children.get(nodeIdx);
        result.append(node.toFancyJson());
        if (!isLastNode(nodeIdx, nrNodes)) {
          result.append(",");
        }
      }
      result.append("]");
    }

    return result.append("}").toString();
  }

  private String create(String key, String value) {
    return "\"" + key + "\":\"" + value + "\"";
  }

  private String createBoolean(String key, boolean value) {
    return "\"" + key + "\":" + String.valueOf(value);
  }

  private boolean isLastNode(int curNode, int nrNodes) {
    return curNode >= nrNodes - 1;
  }

  public Collection<? extends AbstractNode> getChildren() {
    return Collections.EMPTY_LIST;
  }

  public String getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public String getTabLabel() {
    return StringUtils.replace(label, " ", "_");
  }

  public boolean isFolder() {
    return folder;
  }
}
