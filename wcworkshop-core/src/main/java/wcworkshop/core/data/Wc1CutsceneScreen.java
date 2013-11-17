package wcworkshop.core.data;

public class Wc1CutsceneScreen {

  private byte foreground;
  private byte textColor;
  private byte background;
  private byte unknown1;
  private byte unknown2;
  private short conditionOffset;
  private short textOffset;
  private short phoneticOffset;
  private short talkOffset;

  private String condition;
  private String text;
  private String phonetic;
  private String talk;

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

  public short getTalkOffset() {
    return talkOffset;
  }

  public void setTalkOffset(short talkOffset) {
    this.talkOffset = talkOffset;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
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

  public String getTalk() {
    return talk;
  }

  public void setTalk(String talk) {
    this.talk = talk;
  }

}
