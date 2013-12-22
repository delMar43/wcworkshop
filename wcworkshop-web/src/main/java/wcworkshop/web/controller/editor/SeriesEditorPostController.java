package wcworkshop.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeriesEditorPostController {

  @ResponseBody
  @RequestMapping(value = "saveSeries", method = RequestMethod.POST)
  public String saveSeries() {
    return "{\"status\":\"ok\"}";
  }
}
