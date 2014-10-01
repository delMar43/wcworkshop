package wcworkshop.core.data;

public class Wc1NavPointInfo {

  private Wc1NavPointIcon icon;
  private short pointOrShipNr;
  private String text;

  public Wc1NavPointIcon getIcon() {
    return icon;
  }

  public void setIcon(Wc1NavPointIcon icon) {
    this.icon = icon;
  }

  public short getPointOrShipNr() {
    return pointOrShipNr;
  }

  public void setPointOrShipNr(short pointOrShipNr) {
    this.pointOrShipNr = pointOrShipNr;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
