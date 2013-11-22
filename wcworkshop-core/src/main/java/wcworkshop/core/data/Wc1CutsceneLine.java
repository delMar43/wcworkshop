package wcworkshop.core.data;

import java.util.List;

public class Wc1CutsceneLine {

  private String facialExpressions;
  private List<Wc1CutsceneCommand> commands;
  private byte[] commandBytes;
  private String text;
  private String phonetic;

  @Override
  public String toString() {
    return " Commands: " + /*commands() + " | " +*/byteArrayToHexString(commandBytes) + " | Text: " + text + " | Phonetic: " + phonetic
        + " | Facial expressions: " + facialExpressions + "\r\n";
  }

  private String commands() {
    if (commands == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Wc1CutsceneCommand command : commands) {
      sb.append(command.toString());
    }
    return sb.toString();
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
    this.text = text;
  }

  public String getPhonetic() {
    return phonetic;
  }

  public void setPhonetic(String phonetic) {
    this.phonetic = phonetic;
  }

  public byte[] getCommandBytes() {
    return commandBytes;
  }

  public void setCommandBytes(byte[] commandBytes) {
    this.commandBytes = commandBytes;
  }
}
