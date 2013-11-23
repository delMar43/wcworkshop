package wcworkshop.core.writer;

import wcworkshop.core.data.Wc1Savegame;
import wcworkshop.core.reader.ReaderHelper;
import wcworkshop.core.reader.Wc1SavegameOffsets;

public class Wc1SavegameWriter {
  private static final Wc1SavegameWriter instance = new Wc1SavegameWriter();

  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private WriterHelper writerHelper = WriterHelper.getInstance();

  private Wc1SavegameWriter() {
  }

  public void write(Wc1Savegame[] savegames, String filename) {
    byte[] buffer = readerHelper.readFile(filename);

    int index = 0;
    for (Wc1Savegame savegame : savegames) {
      int offset = index++ * Wc1SavegameOffsets.BLOCKSIZE;

      writerHelper.writeToBinary(savegame.getName(), buffer, offset, Wc1SavegameOffsets.NAME_LENGTH);
      buffer[offset + Wc1SavegameOffsets.OFFSET_CAMPAIGN] = savegame.getCampaign();
      buffer[offset + Wc1SavegameOffsets.OFFSET_SERIES] = savegame.getSeries();
      buffer[offset + Wc1SavegameOffsets.OFFSET_MISSION] = savegame.getMission();
    }

    writerHelper.backup(filename);
    writerHelper.writeFile(buffer, filename);
  }

  public static Wc1SavegameWriter getInstance() {
    return instance;
  }
}
