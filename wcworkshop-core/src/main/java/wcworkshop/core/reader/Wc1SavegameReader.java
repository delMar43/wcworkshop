package wcworkshop.core.reader;

import java.util.Arrays;

import wcworkshop.core.data.Wc1Savegame;

public class Wc1SavegameReader {
<<<<<<< HEAD
  private static final Wc1SavegameReader instance = new Wc1SavegameReader();
=======

  private static final Wc1SavegameReader instance = new Wc1SavegameReader();

  private static final short OFFSET_NAME = 0; //size=16
  private static final byte OFFSET_OCCUPIED = 17;//0=empty, 1=used

  private static final short OFFSET_PILOT_NAME = 0; //size=13 (=38*playerIndex+18)
  private static final short OFFSET_PILOT_CALLSIGN = 14; //size=13
  private static final short OFFSET_PILOT_RANK = 16;//short
  private static final short OFFSET_PILOT_SORTIES = 18;//short
  private static final short OFFSET_PILOT_KILLS = 20;//short

  private static final short OFFSET_PILOT1_NAME = 56;//short
  private static final short OFFSET_PILOT1_CALLSIGN = 70;//short
  private static final short OFFSET_PILOT1_RANK = 86;//short 
  private static final short OFFSET_PILOT1_SORTIES = 88;//short
  private static final short OFFSET_PILOT1_KILLS = 90;//short

  private static final short OFFSET_PILOT2_RANK = 124;//short

  private static final short OFFSET_PILOT3_RANK = 162;
  private static final short OFFSET_PILOT3_SORTIES = 164;
  private static final short OFFSET_PILOT3_KILLS = 166;

  private static final short OFFSET_PILOT4_RANK = 200;

  private static final short OFFSET_PILOT5_RANK = 238;

  private static final short OFFSET_PILOT6_RANK = 276;

  private static final short OFFSET_PILOT7_RANK = 314;

  private static final short OFFSET_PLAYER_RANK = 352;
  private static final short OFFSET_PLAYER_SORTIES = 354;
  private static final short OFFSET_PLAYER_KILLS = 356;

  private static final short OFFSET_BRONZE = 364;//byte
  private static final short OFFSET_SILVER = 365;//byte
  private static final short OFFSET_GOLD = 366;//byte
  private static final short OFFSET_GOLDEN_SUN = 367;//byte

  private static final short OFFSET_PILOT_STATUS = 392;//short (=pilotIndex*2+392);
  private static final short OFFSET_PILOT1_STATUS = 394;//short
  private static final short OFFSET_PILOT2_STATUS = 396;//short
  private static final short OFFSET_PILOT3_STATUS = 398;//short
  private static final short OFFSET_PILOT4_STATUS = 400;//short
  private static final short OFFSET_PILOT5_STATUS = 402;//short
  private static final short OFFSET_PILOT6_STATUS = 404;//short
  private static final short OFFSET_PILOT7_STATUS = 406;//short

  private static final short OFFSET_DAY = 412;
  private static final short OFFSET_PROMOTION = 420; //short

  private static final short OFFSET_MISSION = 381;//byte
  private static final short OFFSET_SERIES = 382;//byte

  private static final short OFFSET_BHURAK = 408; //0A = KIA, 01 = not seen, 29 = fled
  private static final short OFFSET_DAKHATH = 409; //0A = KIA, 01 = not seen
  private static final short OFFSET_KHAJJA = 410;//0A = KIA, 01 = not seen
  private static final short OFFSET_BAKTOSH = 411;//0A = KIA, 01 = not seen

  private static final short OFFSET_VICTORY = 424; //short
  private static final short OFFSET_CAMPAIGN = 426;//byte 00=Vega, 01=SM

>>>>>>> branch 'master' of https://github.com/delMar43/wcworkshop.git
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
<<<<<<< HEAD
      if (savegameBlock[Wc1SavegameOffsets.OFFSET_OCCUPIED] == (byte) 1) {
=======
      if (savegameBlock[OFFSET_OCCUPIED] == (byte) 1) {
>>>>>>> branch 'master' of https://github.com/delMar43/wcworkshop.git
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

<<<<<<< HEAD
    savegame.setName(readerHelper.getString(savegameBlock, Wc1SavegameOffsets.NAME_OFFSET, Wc1SavegameOffsets.NAME_LENGTH));

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
=======
    savegame.setName(readerHelper.getString(savegameBlock, OFFSET_NAME, 14));

    for (int pilotIndex = 0; pilotIndex < 8; ++pilotIndex) {
      int pilotOffset = 38 * pilotIndex + 18;

      String name = readerHelper.getString(savegameBlock, pilotOffset + OFFSET_PILOT_NAME, 13);
      String callsign = readerHelper.getString(savegameBlock, pilotOffset + OFFSET_PILOT_CALLSIGN, 13);
      short rank = readerHelper.getShort(savegameBlock, pilotOffset + OFFSET_PILOT_RANK);
      short sorties = readerHelper.getShort(savegameBlock, pilotOffset + OFFSET_PILOT_SORTIES);
      short kills = readerHelper.getShort(savegameBlock, pilotOffset + OFFSET_PILOT_KILLS);

      int pilotStatusOffset = pilotIndex * 2 + 392;
      byte status = savegameBlock[pilotStatusOffset + OFFSET_PILOT_STATUS];
      short day = readerHelper.getShort(savegameBlock, OFFSET_DAY);
      short promotion = readerHelper.getShort(savegameBlock, OFFSET_PROMOTION);
    }

    byte bronzeStars = savegameBlock[OFFSET_BRONZE];
    byte silverStars = savegameBlock[OFFSET_SILVER];
    byte goldStars = savegameBlock[OFFSET_GOLD];
    byte goldenSun = savegameBlock[OFFSET_GOLDEN_SUN];

    byte mission = savegameBlock[OFFSET_MISSION];
    byte series = savegameBlock[OFFSET_SERIES];
    byte campaign = savegameBlock[OFFSET_CAMPAIGN];
    short victoryPoints = readerHelper.getShort(savegameBlock, OFFSET_VICTORY);
>>>>>>> branch 'master' of https://github.com/delMar43/wcworkshop.git

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
