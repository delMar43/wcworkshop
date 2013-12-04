package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wcworkshop.core.data.Wc1Savegame;
import wcworkshop.core.reader.Wc1SavegameReader;
import wcworkshop.web.command.SavegameCommand;

@Controller
public class SavegameEditorController {

  private Wc1SavegameReader savegameReader = Wc1SavegameReader.getInstance();

  @RequestMapping("/savegameEditor")
  public String renderSavegame(Model model) {

    Wc1Savegame[] savegames = savegameReader.read("D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/savegame.wld");
    SavegameCommand command = new SavegameCommand();
    command.setSavegames(savegames);
    model.addAttribute("command", command);

    return "editors/savegameEditor";
  }
}
