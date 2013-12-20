package wcworkshop.core.model.tree;

public abstract class AbstractNode {

  private String label;

  public AbstractNode(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
