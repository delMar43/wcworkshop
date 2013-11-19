package wcworkshop.core.data;

public class Wc1CutsceneLine {

  private String facialExpressions;
  private byte[] commands;
  private String text;
  private String phonetic;

  @Override
  public String toString() {
    return " Commands: " + byteArrayToHexString(commands) + " | Text: " + text + " | Phonetic: " + phonetic + " | Facial expressions: "
        + facialExpressions + "\r\n";
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

  public String getFacialExpressions() {
    return facialExpressions;
  }

  public void setFacialExpressions(String facialExpressions) {
    this.facialExpressions = facialExpressions;
  }

  public byte[] getCommands() {
    return commands;
  }

  public void setCommands(byte[] commands) {
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

}
