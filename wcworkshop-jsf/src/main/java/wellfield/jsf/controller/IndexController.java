package wellfield.jsf.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "index")
@ViewScoped
public class IndexController {

  public String getWord() {
    return "word :)";
  }
}
