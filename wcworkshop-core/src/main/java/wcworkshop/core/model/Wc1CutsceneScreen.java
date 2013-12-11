package wcworkshop.core.model;

public class Wc1CutsceneScreen {

  private byte sequence;
  private String commands;
  private String text;
  private String phonetic;
  private String facialExpression;
  private byte foreground;
  private byte background;
  private byte textColor;

  public byte getSequence() {
    return sequence;
  }

  public void setSequence(byte sequence) {
    this.sequence = sequence;
  }

  public String getCommands() {
    return commands;
  }

  public void setCommands(String commands) {
    this.commands = commands;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getPhonetic() {
    return phonetic;
  }

  public void setPhonetic(String phonetic) {
    this.phonetic = phonetic;
  }

  public String getFacialExpression() {
    return facialExpression;
  }

  public void setFacialExpression(String facialExpression) {
    this.facialExpression = facialExpression;
  }

  public byte getForeground() {
    return foreground;
  }

  public void setForeground(byte foreground) {
    this.foreground = foreground;
  }

  public byte getBackground() {
    return background;
  }

  public void setBackground(byte background) {
    this.background = background;
  }

  public byte getTextColor() {
    return textColor;
  }

  public void setTextColor(byte textColor) {
    this.textColor = textColor;
  }

}
