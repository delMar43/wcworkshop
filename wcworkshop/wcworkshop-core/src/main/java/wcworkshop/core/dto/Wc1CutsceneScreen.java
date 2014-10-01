package wcworkshop.core.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Wc1CutsceneScreen {

  private int sequence;
  private List<Wc1CutsceneCommand> commands;
  private String text;
  private String phonetic;
  private String facialExpression;
  private short unknown;
  private byte foreground;
  private byte background;
  private byte textColor;

  public Wc1CutsceneScreen() {
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public List<Wc1CutsceneCommand> getCommands() {
    return commands;
  }

  public void setCommands(List<Wc1CutsceneCommand> commands) {
    this.commands = commands;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = StringUtils.replace(text, "\r\n", "\n");
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

  public short getUnknown() {
    return unknown;
  }

  public void setUnknown(short unknown) {
    this.unknown = unknown;
  }
}
