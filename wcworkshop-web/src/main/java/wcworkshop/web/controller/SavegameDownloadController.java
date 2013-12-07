package wcworkshop.web.controller;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wcworkshop.core.config.Configuration;
import wcworkshop.core.data.Wc1Savegame;
import wcworkshop.core.data.Wc1SavegameFile;
import wcworkshop.core.reader.ReaderHelper;
import wcworkshop.web.command.SavegameCommand;
import binmap.BinaryReader;
import binmap.BinaryWriter;
import binmap.Mapping;
import binmap.MappingFactory;

@Controller
public class SavegameDownloadController {

  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private Mapping savegameMapping;

  @PostConstruct
  public void afterInitialize() {
    savegameMapping = MappingFactory.getInstance().createMapping("savegame_file.mapping");
  }

  @ResponseBody
  @RequestMapping(value = "/savegame.wld", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public byte[] saveGame(@ModelAttribute SavegameCommand savegameCommand) {
    try {
      return createBinary(savegameCommand);
    } catch (Exception e) {
      throw new RuntimeException("Error while generating savegame file: " + e.getMessage(), e);
    }
  }

  private byte[] createBinary(SavegameCommand savegameCommand) {
    byte[] rawFile = readerHelper.readFile(Configuration.getInstance().getResourcePath() + "SAVEGAME.WLD");

    Wc1SavegameFile patternFile = BinaryReader.getInstance().toJava(rawFile, savegameMapping, Wc1SavegameFile.class);
    Wc1Savegame[] patternSavegames = patternFile.getSavegames();
    Wc1Savegame[] newSavegames = savegameCommand.getSavegames();

    Wc1Savegame[] result = mergeSavegames(patternSavegames, newSavegames);
    Wc1SavegameFile newSavegameFile = new Wc1SavegameFile();
    newSavegameFile.setSavegames(result);

    byte[] binary = BinaryWriter.getInstance().toBinary(newSavegameFile, savegameMapping);

    return binary;
  }

  private Wc1Savegame[] mergeSavegames(Wc1Savegame[] patternSavegames, Wc1Savegame[] newSavegames) {
    Wc1Savegame[] result = new Wc1Savegame[patternSavegames.length];

    for (int idx = 0; idx < patternSavegames.length; ++idx) {
      Wc1Savegame newSavegame = newSavegames[idx];
      result[idx] = mergeSavegame(patternSavegames[idx], newSavegame);
    }

    return result;
  }

  private Wc1Savegame mergeSavegame(Wc1Savegame pattern, Wc1Savegame changed) {
    Wc1Savegame result = changed;

    result.setUnknown1(pattern.getUnknown1());
    result.setUnknown2(pattern.getUnknown2());
    result.setUnknown3(pattern.getUnknown3());
    result.setUnknown4(pattern.getUnknown4());
    result.setUnknown5(pattern.getUnknown5());
    result.setUnknownBlock1(pattern.getUnknownBlock1());
    result.setUnknownBlock2(pattern.getUnknownBlock2());
    result.setUnknownBlock3(pattern.getUnknownBlock3());
    result.setUnknownBlock4(pattern.getUnknownBlock4());

    return result;
  }
}
