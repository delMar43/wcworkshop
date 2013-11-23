package wcworkshop.core.reader;

public interface Wc1SavegameOffsets {

  int BLOCKSIZE = 828;

  short NAME_OFFSET = 0;
  byte NAME_LENGTH = 14;
  byte OFFSET_OCCUPIED = 17;//0=empty, 1=used

  short OFFSET_PILOT_NAME = 0; //size=13 (=38*playerIndex+18)
  short OFFSET_PILOT_CALLSIGN = 14; //size=13
  short OFFSET_PILOT_RANK = 16;//short
  short OFFSET_PILOT_SORTIES = 18;//short
  short OFFSET_PILOT_KILLS = 20;//short

  short OFFSET_PILOT1_NAME = 56;//short
  short OFFSET_PILOT1_CALLSIGN = 70;//short
  short OFFSET_PILOT1_RANK = 86;//short 
  short OFFSET_PILOT1_SORTIES = 88;//short
  short OFFSET_PILOT1_KILLS = 90;//short

  short OFFSET_PILOT2_RANK = 124;//short

  short OFFSET_PILOT3_RANK = 162;
  short OFFSET_PILOT3_SORTIES = 164;
  short OFFSET_PILOT3_KILLS = 166;

  short OFFSET_PILOT4_RANK = 200;

  short OFFSET_PILOT5_RANK = 238;

  short OFFSET_PILOT6_RANK = 276;

  short OFFSET_PILOT7_RANK = 314;

  short OFFSET_PLAYER_RANK = 352;
  short OFFSET_PLAYER_SORTIES = 354;
  short OFFSET_PLAYER_KILLS = 356;

  short OFFSET_BRONZE = 364;//byte
  short OFFSET_SILVER = 365;//byte
  short OFFSET_GOLD = 366;//byte
  short OFFSET_GOLDEN_SUN = 367;//byte

  short OFFSET_PILOT_STATUS = 392;//short (=pilotIndex*2+392);
  short OFFSET_PILOT1_STATUS = 394;//short
  short OFFSET_PILOT2_STATUS = 396;//short
  short OFFSET_PILOT3_STATUS = 398;//short
  short OFFSET_PILOT4_STATUS = 400;//short
  short OFFSET_PILOT5_STATUS = 402;//short
  short OFFSET_PILOT6_STATUS = 404;//short
  short OFFSET_PILOT7_STATUS = 406;//short

  short OFFSET_DAY = 412;
  short OFFSET_PROMOTION = 420; //short

  short OFFSET_MISSION = 381;//byte
  short OFFSET_SERIES = 382;//byte

  short OFFSET_BHURAK = 408; //0A = KIA, 01 = not seen, 29 = fled
  short OFFSET_DAKHATH = 409; //0A = KIA, 01 = not seen
  short OFFSET_KHAJJA = 410;//0A = KIA, 01 = not seen
  short OFFSET_BAKTOSH = 411;//0A = KIA, 01 = not seen

  short OFFSET_VICTORY = 424; //short
  short OFFSET_CAMPAIGN = 426;//byte 00=Vega, 01=SM
}
