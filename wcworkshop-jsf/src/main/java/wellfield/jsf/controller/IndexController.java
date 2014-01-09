package wellfield.jsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@SuppressWarnings("serial")
@ManagedBean(name = "index")
public class IndexController implements Serializable {

  public String getWord() {
    return "word :)";
  }
}
