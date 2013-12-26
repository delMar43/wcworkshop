package wcworkshop.core.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Wc1CutsceneCommand {

  private byte code;
  private List<String> parameters = new ArrayList<>();

  @JsonIgnore
  public String getCodeString() {
    return String.valueOf(code);
  }

  @JsonIgnore
  public void setCodeString(String code) {
    this.code = Byte.parseByte(code);
  }

  public byte getCode() {
    return code;
  }

  public void setCode(byte code) {
    this.code = code;
  }

  public void appendParameter(String parameter) {
    parameters.add(parameter);
  }

  public void setParameters(List<String> parameters) {
    this.parameters = parameters;
  }

  @JsonIgnore
  public String getParameterString() {
    return StringUtils.join(parameters, ",");
  }

  public void setParameterString(String paramString) {
    String[] entities = paramString.split(",");
    parameters = Arrays.asList(entities);
  }

  public List<String> getParameters() {
    return parameters;
  }

  @Override
  public String toString() {
    return String.valueOf(code) + "(" + getParameters() + ")";
  }
}
