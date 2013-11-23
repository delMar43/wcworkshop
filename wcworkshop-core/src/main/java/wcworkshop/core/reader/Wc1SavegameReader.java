package wcworkshop.core.reader;

import wcworkshop.core.data.Wc1Savegame;

public class Wc1SavegameReader {
  private static final short OFFSET_NAME = 0; //size=16

  private static final short OFFSET_PILOT0_NAME = 18; //size=13
  private static final short OFFSET_PILOT0_CALLSIGN = 32; //size=13
  private static final short OFFSET_PILOT0_RANK = 48;//short
  private static final short OFFSET_PILOT0_SORTIES = 50;//short
  private static final short OFFSET_PILOT0_KILLS = 52;//short
  private static final short OFFSET_PILOT0_CODE1 = 54;//byte
  private static final short OFFSET_PILOT0_CODE2 = 55;//byte

  private static final short OFFSET_PILOT1_RANK = 86;//short (=38*playerIndex+48)
  private static final short OFFSET_PILOT1_SORTIES = 88;//short
  private static final short OFFSET_PILOT1_KILLS = 90;//short
  private static final short OFFSET_PILOT1_CODE1 = 92;// byte
  private static final short OFFSET_PILOT1_CODE2 = 93;//byte

  private static final short OFFSET_PILOT2_RANK = 124;//short

  private static final short OFFSET_PILOT3_RANK = 162;
  private static final short OFFSET_PILOT3_SORTIES = 164;
  private static final short OFFSET_PILOT3_KILLS = 166;
  private static final short OFFSET_PILOT3_CODE1 = 168;
  private static final short OFFSET_PILOT3_CODE2 = 169;

  private static final short OFFSET_PILOT4_RANK = 200;

  private static final short OFFSET_PILOT5_RANK = 238;

  private static final short OFFSET_PILOT6_RANK = 276;

  private static final short OFFSET_PILOT7_RANK = 314;

  private static final short OFFSET_PLAYER_RANK = 352;
  private static final short OFFSET_PLAYER_SORTIES = 354;
  private static final short OFFSET_PLAYER_KILLS = 356;
  private static final short OFFSET_PLAYER_CODE1 = 358;
  private static final short OFFSET_PLAYER_CODE2 = 359;

  private static final short OFFSET_BRONZE = 364;//byte
  private static final short OFFSET_SILVER = 365;//byte
  private static final short OFFSET_GOLD = 366;//byte
  private static final short OFFSET_GOLDEN_SUN = 367;//byte

  private static final short OFFSET_PILOT0_STATUS = 392;//short
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

  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1Savegame read(String path) {
    Wc1Savegame result = new Wc1Savegame();

    byte[] file = readerHelper.readFile(path);
    short blocksize = 828;
    for (int index = 0; index + blocksize < file.length; index += blocksize) {

    }

    return result;
  }
}
