package wcworkshop.core.model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public abstract class AbstractNode implements Serializable {

  private String key;
  private String title;
  private boolean folder;
  private Map<String, String> data;

  public AbstractNode(String id, String label, boolean folder) {
    this(id, label, folder, new HashMap<String, String>());
  }

  public AbstractNode(String key, String title, boolean folder, Map<String, String> data) {
    this.key = key;
    this.title = title;
    this.folder = folder;
    this.data = new HashMap<>(data);
    this.data.put("title", title);
  }

  @JsonIgnore
  public String toFancyJson() {
    StringBuilder result = new StringBuilder("{");

    result.append(create("title", title));
    result.append(",");
    result.append(create("key", key));

    if (!CollectionUtils.isEmpty(data)) {
      result.append(",");
      result.append("\"data\":[");
      int entryIdx = 0;
      int nrEntries = data.size();
      for (Entry<String, String> entry : data.entrySet()) {
        result.append("{" + create(entry.getKey(), entry.getValue()) + "}");
        if (!isLastNode(entryIdx++, nrEntries)) {
          result.append(",");
        }
      }
      result.append("]");
    }

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

  public String getKey() {
    return key;
  }

  public String getTitle() {
    return title;
  }

  @JsonIgnore
  public String getTabLabel() {
    return StringUtils.replace(title, " ", "_");
  }

  public boolean isFolder() {
    return folder;
  }

  public Map<String, String> getData() {
    return data;
  }
}
