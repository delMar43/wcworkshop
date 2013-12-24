package wcworkshop.core.dto;

public class Wc1CutsceneCommand {

  private byte code;
  private StringBuilder parameters = new StringBuilder();

  public byte getCode() {
    return code;
  }

  public void setCode(byte code) {
    this.code = code;
  }

  public void appendParameter(String parameter) {
    parameters.append(parameter);
  }

  public String getParameters() {
    return parameters.toString();
  }
}
