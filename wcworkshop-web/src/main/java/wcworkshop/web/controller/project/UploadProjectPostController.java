package wcworkshop.web.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UploadProjectPostController {

  @ResponseBody
  @RequestMapping(value = "/uploadProject", method = RequestMethod.POST)
  public String upload(@ModelAttribute Object command) {

    return "{}";
  }
}
