package wcworkshop.core.data;

import java.util.List;

public class Wc1Cutscene {

  private byte foreground;
  private byte textColor;
  private byte background;
  private byte unknown1;
  private byte unknown2;
  private short conditionOffset;
  private short firstLineOffset;
  private short firstLinePhoneticOffset;
  private short talkOffset;

  private byte[] condition;
  private String firstLine;
  private String firstLinePhonetic;
  private List<Wc1CutsceneLine> cutsceneLines;

  @Override
  public String toString() {
    return " FG: " + hex(foreground) + " | TC: " + hex(textColor) + " | BG: " + hex(background) + " | unknown1: " + hex(unknown1)
        + " | unknown2: " + hex(unknown2) + " | Condition: " + byteArrayToHexString(condition) + " | first line: " + firstLine
        + " | lines: " + cutsceneLinesText();
  }

  private String byteArrayToHexString(byte[] input) {
    if (input == null) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    for (byte b : input) {
      result.append("0x" + Integer.toHexString(b) + " ");
    }
    return result.toString();
  }

  private String cutsceneLinesText() {
    if (cutsceneLines == null) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    for (Wc1CutsceneLine line : cutsceneLines) {
      result.append(line.toString());
    }
    return result.toString();
  }

  private String hex(int value) {
    return "0x" + Integer.toHexString(value).toUpperCase();
  }

  public byte getForeground() {
    return foreground;
  }

  public void setForeground(byte foreground) {
    this.foreground = foreground;
  }

  public byte getTextColor() {
    return textColor;
  }

  public void setTextColor(byte textColor) {
    this.textColor = textColor;
  }

  public byte getBackground() {
    return background;
  }

  public void setBackground(byte background) {
    this.background = background;
  }

  public byte getUnknown1() {
    return unknown1;
  }

  public void setUnknown1(byte unknown1) {
    this.unknown1 = unknown1;
  }

  public byte getUnknown2() {
    return unknown2;
  }

  public void setUnknown2(byte unknown2) {
    this.unknown2 = unknown2;
  }

  public short getConditionOffset() {
    return conditionOffset;
  }

  public void setConditionOffset(short conditionOffset) {
    this.conditionOffset = conditionOffset;
  }

  public short getFirstLineOffset() {
    return firstLineOffset;
  }

  public void setFirstLineOffset(short offset) {
    this.firstLineOffset = offset;
  }

  public short getFirstLinePhoneticOffset() {
    return firstLinePhoneticOffset;
  }

  public void setFirstLinePhoneticOffset(short offset) {
    this.firstLinePhoneticOffset = offset;
  }

  public short getTalkOffset() {
    return talkOffset;
  }

  public void setTalkOffset(short talkOffset) {
    this.talkOffset = talkOffset;
  }

  public byte[] getCondition() {
    return condition;
  }

  public void setCondition(byte[] condition) {
    this.condition = condition;
  }

  public String getFirstLine() {
    return firstLine;
  }

  public void setFirstLine(String text) {
    this.firstLine = text;
  }

  public String getFirstLinePhonetic() {
    return firstLinePhonetic;
  }

  public void setFirstLinePhonetic(String phonetic) {
    this.firstLinePhonetic = phonetic;
  }

  public List<Wc1CutsceneLine> getCutsceneLines() {
    return cutsceneLines;
  }

  public void setCutsceneLines(List<Wc1CutsceneLine> cutsceneLines) {
    this.cutsceneLines = cutsceneLines;
  }
}
