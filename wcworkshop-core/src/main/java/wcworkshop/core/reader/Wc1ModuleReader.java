package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1MissionShipData;
import wcworkshop.core.data.Wc1ModuleData;
import wcworkshop.core.data.Wc1NavPoint;
import wcworkshop.core.data.Wc1NavPointIcon;
import wcworkshop.core.data.Wc1NavPointInfo;
import wcworkshop.core.data.Wc1NavPointManipulation;

public class Wc1ModuleReader {
  private static final Wc1ModuleReader instance = new Wc1ModuleReader();

  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1ModuleData readModuleFile(String path) {
    byte[] buffer = readerHelper.readFile(path);
    if (buffer == null) {
      return Wc1ModuleData.EMPTY;
    }

    Wc1ModuleData result = new Wc1ModuleData();
    int filesize = readerHelper.extractFilesize(buffer);
    List<Integer> blockOffsets = readerHelper.extractBlockOffsets(buffer);
    //    System.out.println("blocks: " + Arrays.toString(blockOffsets.toArray()));

    List<List<Short>> autopilotShips = extractBlock1(Arrays.copyOfRange(buffer, blockOffsets.get(0), blockOffsets.get(1)));
    result.setAutopilotShips(autopilotShips);

    List<Wc1NavPoint> navPoints = extractNavPoints(Arrays.copyOfRange(buffer, blockOffsets.get(1), blockOffsets.get(2)));
    result.setNavPoints(navPoints);

    List<Wc1NavPointInfo> navPointInfos = extractNavPointInfos(Arrays.copyOfRange(buffer, blockOffsets.get(2), blockOffsets.get(3)));
    result.setNavPointInfo(navPointInfos);

    List<Wc1MissionShipData> shipNavData = extractShipMissionData(Arrays.copyOfRange(buffer, blockOffsets.get(3), blockOffsets.get(4)));
    result.setMissionShipData(shipNavData);

    List<String> wingNames = extractSizedBlock(Arrays.copyOfRange(buffer, blockOffsets.get(4), blockOffsets.get(5)), 160, 40);
    result.setWingNames(wingNames);

    List<String> systemNames = extractSizedBlock(Arrays.copyOfRange(buffer, blockOffsets.get(5), filesize), 0x28, 40);
    result.setSystemNames(systemNames);

    return result;
  }

  private List<List<Short>> extractBlock1(byte[] buffer) {
    int startOffset = 100;
    int chunkSize = 24;

    List<List<Short>> autopilotInfo = new ArrayList<>();

    for (int missionIndex = 0; missionIndex < 52; ++missionIndex) {
      List<Short> autopilotShips = new ArrayList<>();
      int missionOffset = startOffset + (missionIndex * chunkSize);
      for (int shipIdx = 0; shipIdx < 5; ++shipIdx) {
        int offset = missionOffset + shipIdx * 2;
        short ship = readerHelper.getShort(buffer, offset);
        if (ship == -1) {
          break;
        }
        autopilotShips.add(ship);
      }
      autopilotInfo.add(autopilotShips);
    }

    return autopilotInfo;
  }

  /**
   * contains nav point data
   */
  private List<Wc1NavPoint> extractNavPoints(byte[] buffer) {
    // nav points start at relative 0x1340, chunk length=0x4d (77 dez)
    int offset = 0x1340;
    int chunkSize = 77;
    int navsPerMission = 16;
    List<Wc1NavPoint> result = new ArrayList<>();
    int curOffset = offset;
    for (int series = 0; series < 13; ++series) {
      for (int mission = 0; mission < 4; ++mission) {
        for (int nav = 0; nav < navsPerMission; ++nav) {
          byte[] curBuffer = Arrays.copyOfRange(buffer, curOffset, curOffset + chunkSize);
          Wc1NavPoint navPoint = extractNavPointData(curBuffer);
          result.add(navPoint);
          curOffset += chunkSize;
        }
      }
    }
    return result;
  }

  private Wc1NavPoint extractNavPointData(byte[] curBuffer) {
    Wc1NavPoint navPoint = new Wc1NavPoint();

    String id = new String(Arrays.copyOfRange(curBuffer, 0, 30));
    navPoint.setId(id.substring(0, id.indexOf("\0")));
    navPoint.setVisible(!id.startsWith("."));
    navPoint.setType(curBuffer[30]);

    byte[] xBytes = Arrays.copyOfRange(curBuffer, 32, 36);
    navPoint.setUnknown1(curBuffer[35]);
    xBytes[3] = 0;
    navPoint.setxPos(readerHelper.getInteger(xBytes));
    byte[] yBytes = Arrays.copyOfRange(curBuffer, 36, 40);
    navPoint.setUnknown2(curBuffer[39]);
    yBytes[3] = 0;
    navPoint.setyPos(readerHelper.getInteger(yBytes));
    byte[] zBytes = Arrays.copyOfRange(curBuffer, 40, 44);
    navPoint.setUnknown3(curBuffer[43]);
    zBytes[3] = 0;
    navPoint.setzPos(readerHelper.getInteger(zBytes));

    List<Wc1NavPointManipulation> navManList = new ArrayList<>();
    for (int i = 0; i < 8; i += 2) {
      byte enableByte = curBuffer[45 + i];
      if (enableByte == (byte) 0xff) {
        break;
      }
      Wc1NavPointManipulation npm = new Wc1NavPointManipulation();
      npm.setEnableNavPoint(enableByte == 1);
      npm.setNavPoint(curBuffer[46 + i]);
      navManList.add(npm);
    }
    navPoint.setNavPointManipulations(navManList);

    List<Short> shipImagesToLoad = new ArrayList<>();
    for (int i = 0; i < 3; i += 2) {
      short shipImageToLoad = readerHelper.getShort(new byte[] { curBuffer[i + 53], curBuffer[i + 54] });
      if (shipImageToLoad == (short) 0xffff) {
        break;
      }
      shipImagesToLoad.add(shipImageToLoad);
    }
    navPoint.setShipImagesToLoad(shipImagesToLoad);

    List<Short> presentShips = new ArrayList<>();
    for (int i = 0; i < 20; i += 2) {
      short shipIndex = readerHelper.getShort(curBuffer, 57 + i);
      if (shipIndex == -1) {
        break;
      }
      presentShips.add(shipIndex);
    }
    navPoint.setPresentShips(presentShips);

    return navPoint;
  }

  private List<Wc1NavPointInfo> extractNavPointInfos(byte[] buffer) {
    int start = 0x1000;
    int chunkSize = 64;
    List<Wc1NavPointInfo> result = new ArrayList<>();
    for (int offset = start; offset < buffer.length; offset += chunkSize) {
      Wc1NavPointInfo info = new Wc1NavPointInfo();

      short iconValue = readerHelper.getShort(buffer, offset);
      Wc1NavPointIcon icon = Wc1NavPointIcon.getByValue(iconValue);
      short shipOrPoint = readerHelper.getShort(buffer, offset + 2);
      String text = readerHelper.getString(buffer, offset + 4, 60);

      info.setIcon(icon);
      info.setPointOrShipNr(shipOrPoint);
      info.setText(text);

      result.add(info);
    }

    return result;
  }

  private List<Wc1MissionShipData> extractShipMissionData(byte[] buffer) {
    //    145948: start of this buffer
    //    151324: ship stuff (139776 ?)
    //    231963: end of this buffer
    int startOffset = 5376;
    int blockSize = 42;
    int endOffset = startOffset + 32 * 52 * blockSize; //52 missions with 32 ships each
    List<Wc1MissionShipData> navData = new ArrayList<>(52);
    for (int offset = startOffset; offset < endOffset; offset += blockSize) {
      Wc1MissionShipData shipData = extractShipNavData(buffer, offset);
      navData.add(shipData);
    }
    return navData;
  }

  private Wc1MissionShipData extractShipNavData(byte[] buffer, int offset) {
    Wc1MissionShipData result = new Wc1MissionShipData();

    result.setType(buffer[offset]);
    result.setIff(buffer[offset + 2]);
    result.setOrders(buffer[offset + 6]);
    result.setxCoord(readerHelper.getInteger(buffer, offset + 10, 3));
    result.setyCoord(readerHelper.getInteger(buffer, offset + 14, 3));
    result.setzCoord(readerHelper.getInteger(buffer, offset + 18, 3));
    result.setSpeed(buffer[offset + 28]);
    result.setSize(buffer[offset + 29]);
    result.setAiPilot(buffer[offset + 32]);
    result.setSecondaryTarget(buffer[offset + 39]);
    result.setFormation(buffer[offset + 40]);
    result.setPrimaryTarget(buffer[offset + 41]);

    return result;
  }

  private List<String> extractSizedBlock(byte[] buffer, int startOffset, int chunkSize) {
    List<String> result = new ArrayList<>();
    for (int index = startOffset; index < buffer.length; index += chunkSize) {
      String raw = new String(Arrays.copyOfRange(buffer, index, index + chunkSize));
      raw = raw.substring(0, raw.indexOf("\0"));
      result.add(raw);
    }
    return result;
  }

  public static Wc1ModuleReader getInstance() {
    return instance;
  }
}
