package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeriesEditorController {

  @RequestMapping("/seriesEditor.html")
  public String renderEditor(@RequestParam int seriesIndex, Model model) {

    model.addAttribute("series", seriesIndex);

    return "editors/seriesEditor";
  }
}
