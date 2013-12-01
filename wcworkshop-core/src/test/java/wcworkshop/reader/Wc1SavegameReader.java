package wcworkshop.reader;

import java.util.Arrays;

import wcworkshop.core.data.Wc1Savegame;
import wcworkshop.core.reader.ReaderHelper;
import wcworkshop.core.reader.Wc1SavegameOffsets;

public class Wc1SavegameReader {
  private static final Wc1SavegameReader instance = new Wc1SavegameReader();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1Savegame[] read(String path) {
    Wc1Savegame[] result = new Wc1Savegame[8];

    byte[] file = readerHelper.readFile(path);
    int resultIndex = 0;
    short blocksize = 828;
    for (int index = 0; index < 8; ++index) {
      int offset = index * 828;
      byte[] savegameBlock = Arrays.copyOfRange(file, offset, offset + blocksize);
      Wc1Savegame savegame;
      if (savegameBlock[Wc1SavegameOffsets.OFFSET_OCCUPIED] == (byte) 1) {
        savegame = extractSavegame(savegameBlock);
      } else {
        savegame = Wc1Savegame.EMPTY;
      }
      result[resultIndex++] = savegame;
    }

    return result;
  }

  private Wc1Savegame extractSavegame(byte[] savegameBlock) {
    Wc1Savegame savegame = new Wc1Savegame();

    savegame.setName(readerHelper.getString(savegameBlock, Wc1SavegameOffsets.NAME_OFFSET, 14));

    for (int pilotIndex = 0; pilotIndex < 8; ++pilotIndex) {
      int pilotOffset = 38 * pilotIndex + 18;

      String name = readerHelper.getString(savegameBlock, pilotOffset + Wc1SavegameOffsets.OFFSET_PILOT_NAME, 13);
      String callsign = readerHelper.getString(savegameBlock, pilotOffset + Wc1SavegameOffsets.OFFSET_PILOT_CALLSIGN, 13);
      short rank = readerHelper.getShort(savegameBlock, pilotOffset + Wc1SavegameOffsets.OFFSET_PILOT_RANK);
      short sorties = readerHelper.getShort(savegameBlock, pilotOffset + Wc1SavegameOffsets.OFFSET_PILOT_SORTIES);
      short kills = readerHelper.getShort(savegameBlock, pilotOffset + Wc1SavegameOffsets.OFFSET_PILOT_KILLS);

      int pilotStatusOffset = pilotIndex * 2 + 392;
      byte status = savegameBlock[pilotStatusOffset + Wc1SavegameOffsets.OFFSET_PILOT_STATUS];
      short day = readerHelper.getShort(savegameBlock, Wc1SavegameOffsets.OFFSET_DAY);
      short promotion = readerHelper.getShort(savegameBlock, Wc1SavegameOffsets.OFFSET_PROMOTION);
    }

    byte bronzeStars = savegameBlock[Wc1SavegameOffsets.OFFSET_BRONZE];
    byte silverStars = savegameBlock[Wc1SavegameOffsets.OFFSET_SILVER];
    byte goldStars = savegameBlock[Wc1SavegameOffsets.OFFSET_GOLD];
    byte goldenSun = savegameBlock[Wc1SavegameOffsets.OFFSET_GOLDEN_SUN];

    byte mission = savegameBlock[Wc1SavegameOffsets.OFFSET_MISSION];
    byte series = savegameBlock[Wc1SavegameOffsets.OFFSET_SERIES];
    byte campaign = savegameBlock[Wc1SavegameOffsets.OFFSET_CAMPAIGN];
    short victoryPoints = readerHelper.getShort(savegameBlock, Wc1SavegameOffsets.OFFSET_VICTORY);

    byte bhurak = savegameBlock[408];
    byte dakhath = savegameBlock[409];
    byte khajja = savegameBlock[410];
    byte baktosh = savegameBlock[411];

    savegame.setCampaign(campaign);
    savegame.setSeries(series);
    savegame.setMission(mission);

    return savegame;
  }

  public static Wc1SavegameReader getInstance() {
    return instance;
  }
}
