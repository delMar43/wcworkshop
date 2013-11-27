package wcworkshop.core.data;

public class Wc1CutsceneCommand {

  private byte code;
  private boolean withParameter;

  Wc1CutsceneCommand(byte code) {
    this.code = code;
  }

  Wc1CutsceneCommand(byte code, boolean withParameter) {
    this(code);
    this.withParameter = withParameter;
  }

  public byte getCode() {
    return code;
  }

  public boolean isWithParameter() {
    return withParameter;
  }

}
