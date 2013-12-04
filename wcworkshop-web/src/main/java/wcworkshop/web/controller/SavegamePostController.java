package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wcworkshop.web.command.SavegameCommand;

@Controller
public class SavegamePostController {

  @RequestMapping(value = "/savegame", method = RequestMethod.POST)
  public String saveGame(@ModelAttribute SavegameCommand savegameCommand) {

    // writer.write(savegameCommand.getSavegames(), "D:/Users/martin/Dropbox/dev/wcworkshop/gamedat/wc1/savegame.wld");

    return "forward:savegameEditor.html";
  }
}
