package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wcworkshop.core.config.Configuration;
import wcworkshop.core.data.Wc1SavegameFile;
import wcworkshop.core.reader.ReaderHelper;
import wcworkshop.web.command.SavegameCommand;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

@Controller
public class SavegameEditorController {

  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  @RequestMapping("/savegameEditor")
  public String renderSavegame(Model model) {
    Mapping mapper = mappingFactory.createMapping("savegame_file.mapping");

    //    byte[] data = readerHelper.readFile("D:/spiele/Wing Commander 1 and 2/WC/GAMEDAT/SAVEGAME.WLD");
    byte[] data = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "SAVEGAME.WLD");

    Wc1SavegameFile file = BinaryReader.getInstance().toJava(data, mapper, Wc1SavegameFile.class);

    SavegameCommand command = new SavegameCommand();
    command.setSavegames(file.getSavegames());
    model.addAttribute("command", command);

    return "editors/savegameEditor";
  }
}
