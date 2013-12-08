package wcworkshop.web.command;

import wcworkshop.core.binary.Wc1Savegame;

public class SavegameCommand {
  private Wc1Savegame[] savegames = new Wc1Savegame[8];

  public Wc1Savegame[] getSavegames() {
    return savegames;
  }

  public void setSavegames(Wc1Savegame[] savegames) {
    this.savegames = savegames;
  }
}
