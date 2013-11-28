package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    System.out.println("blocks: " + Arrays.toString(blockOffsets.toArray()));

    //    System.out.println("Block 1:");
    //    extractBlock1(result, Arrays.copyOfRange(buffer, blockOffsets.get(1), blockOffsets.get(2)));

    List<Wc1NavPoint> navPoints = extractNavPoints(Arrays.copyOfRange(buffer, blockOffsets.get(1), blockOffsets.get(2)));
    result.setNavPoints(navPoints);

    List<Wc1NavPointInfo> navPointInfos = extractThirdBlock(Arrays.copyOfRange(buffer, blockOffsets.get(2), blockOffsets.get(3)));
    result.setNavPointInfo(navPointInfos);

    //    System.out.println("Block 4:");
    //    extractSizedBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(3), result.getBlockOffset(4)), result.getBlockOffset(3));

    List<String> wingNames = extractSizedBlock(Arrays.copyOfRange(buffer, blockOffsets.get(4), blockOffsets.get(5)), 160, 40);
    result.setWingNames(wingNames);

    List<String> systemNames = extractSizedBlock(Arrays.copyOfRange(buffer, blockOffsets.get(5), filesize), 0x28, 40);
    result.setSystemNames(systemNames);

    return result;
  }

  private void extractBlock1(Wc1ModuleData result, byte[] buffer) {
    int chunkLength = 0x30;
    for (int index = 0; index < buffer.length; index += chunkLength) {
      System.out.println(index + ": " + readerHelper.byteArrayToHexString(Arrays.copyOfRange(buffer, index, index + chunkLength)));
    }
  }

  /**
   * contains nav point data
   */
  private List<Wc1NavPoint> extractNavPoints(byte[] buffer) {
    // nav points start at relative 0x1340, chunk length=0x4d (77 dez)
    int offset = 0x1340;
    int chunkSize = 77;
    int navsPerMission = 16;
    int previousIndex = offset;
    List<Wc1NavPoint> result = new ArrayList<>();
    //    for (int index = offset; index < buffer.length; index += chunkSize) {
    int index = offset;
    int curOffset = offset;
    for (int series = 0; series < 13; ++series) {
      for (int mission = 0; mission < 4; ++mission) {
        for (int nav = 0; nav < navsPerMission; ++nav) {
          //          int curOffset = offset + (nav * 77) + (mission * 16 * 77) + (series * 64 * 77);
          System.out.println("S" + (series + 1) + "M" + (mission + 1) + "N" + (nav + 1) + " - " + curOffset);

          byte[] curBuffer = Arrays.copyOfRange(buffer, curOffset, curOffset + chunkSize);
          Wc1NavPoint navPoint = extractNavPointData(curBuffer);
          result.add(navPoint);
          System.out.println((index - previousIndex) + " " + index + " 0x" + Integer.toHexString(index).toUpperCase() + " - "
              + navPoint.toString());
          previousIndex = index;
          index += chunkSize;
          curOffset += chunkSize;
        }
        System.out.println("---");
      }
    }
    return result;
  }

  private Wc1NavPoint extractNavPointData(byte[] curBuffer) {
    Wc1NavPoint navPoint = new Wc1NavPoint();

    String id = new String(Arrays.copyOfRange(curBuffer, 0, 30));
    navPoint.setId(id.substring(0, id.indexOf("\0")));
    navPoint.setVisible(!id.startsWith("."));
    navPoint.setUnknown1(readerHelper.getShort(Arrays.copyOfRange(curBuffer, 30, 32)));

    byte[] xBytes = Arrays.copyOfRange(curBuffer, 32, 34);
    navPoint.setxPos(readerHelper.getShort(xBytes));
    navPoint.setUnknown2(readerHelper.getShort(new byte[] { curBuffer[34], curBuffer[35] }));
    byte[] yBytes = Arrays.copyOfRange(curBuffer, 36, 38);
    navPoint.setyPos(readerHelper.getShort(yBytes));
    navPoint.setInSystemJumpPoint(curBuffer[38]);
    navPoint.setIsJumpPoint(curBuffer[39]);

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

    List<Short> shipsToLoad = new ArrayList<>();
    for (int i = 0; i < 3; i += 2) {
      short shipToLoad = readerHelper.getShort(new byte[] { curBuffer[i + 53], curBuffer[i + 54] });
      if (shipToLoad == (short) 0xffff) {
        break;
      }
      shipsToLoad.add(shipToLoad);
    }
    navPoint.setShipsToLoad(shipsToLoad);

    return navPoint;
  }

  private List<Wc1NavPointInfo> extractThirdBlock(byte[] buffer) {
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
