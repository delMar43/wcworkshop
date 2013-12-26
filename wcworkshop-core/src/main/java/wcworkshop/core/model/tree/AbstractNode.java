package wcworkshop.core.model.tree;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractNode {

  private String id;
  private String label;

  public AbstractNode(String id, String label) {
    this.id = id;
    this.label = label;
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
}
