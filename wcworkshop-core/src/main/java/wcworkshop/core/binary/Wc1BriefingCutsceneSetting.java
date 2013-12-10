package wcworkshop.core.binary;

public class Wc1BriefingCutsceneSetting {
  private byte foreground;
  private byte fontColor;
  private byte background;
  private short unknown;
  private short commandOffset;
  private short textOffset;
  private short phoneticOffset;
  private short facialExpressionOffset;

  public byte getForeground() {
    return foreground;
  }

  public void setForeground(byte foreground) {
    this.foreground = foreground;
  }

  public byte getFontColor() {
    return fontColor;
  }

  public void setFontColor(byte fontColor) {
    this.fontColor = fontColor;
  }

  public byte getBackground() {
    return background;
  }

  public void setBackground(byte background) {
    this.background = background;
  }

  public short getUnknown() {
    return unknown;
  }

  public void setUnknown(short unknown) {
    this.unknown = unknown;
  }

  public short getCommandOffset() {
    return commandOffset;
  }

  public void setCommandOffset(short commandOffset) {
    this.commandOffset = commandOffset;
  }

  public short getTextOffset() {
    return textOffset;
  }

  public void setTextOffset(short textOffset) {
    this.textOffset = textOffset;
  }

  public short getPhoneticOffset() {
    return phoneticOffset;
  }

  public void setPhoneticOffset(short phoneticOffset) {
    this.phoneticOffset = phoneticOffset;
  }

  public short getFacialExpressionOffset() {
    return facialExpressionOffset;
  }

  public void setFacialExpressionOffset(short facialExpressionOffset) {
    this.facialExpressionOffset = facialExpressionOffset;
  }

}
