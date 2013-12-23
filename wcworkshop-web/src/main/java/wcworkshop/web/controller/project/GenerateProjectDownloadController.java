package wcworkshop.web.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GenerateProjectDownloadController {

  @ResponseBody
  @RequestMapping("/download/{filename}")
  public byte[] deliverFile(@RequestParam String filename) {

    return null;
  }
}
