package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1ModuleData;
import wcworkshop.core.data.Wc1NavPoint;
import wcworkshop.core.data.Wc1NavPointManipulation;

public class Wc1ModuleReader {

  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1ModuleData readModuleFile(String path) {
    byte[] buffer = readerHelper.readFile(path);
    if (buffer == null) {
      return Wc1ModuleData.EMPTY;
    }

    Wc1ModuleData result = new Wc1ModuleData();
    readerHelper.extractFilesize(result, buffer);
    readerHelper.extractBlockOffsets(result, buffer);

    // System.out.println("Block 1:");
    // extractBlock1(result, Arrays.copyOfRange(buffer, result.getBlockOffset(1), result.getBlockOffset(2)));

    System.out.println("Block 2:");
    extractBlock2(result, Arrays.copyOfRange(buffer, result.getBlockOffset(1), result.getBlockOffset(2)));

    System.out.println("Block 3:");
    extractThirdBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(2), result.getBlockOffset(3)), result.getBlockOffset(2));

    System.out.println("Block 5:");
    extractSizedBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(4), result.getBlockOffset(5)), result.getBlockOffset(4), 40);

    System.out.println("Block 6:");
    extractSizedBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(5), result.getFilesize()), result.getBlockOffset(5), 40);
    // extractFirstBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(0), result.getBlockOffset(1)));
    // extractSecondBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(1), result.getBlockOffset(2)));
    // extractThirdBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(2), result.getFilesize()));

    return result;
  }

  private void extractBlock1(Wc1ModuleData result, byte[] buffer) {
    int chunkLength = 0x30;
    for (int index = 0; index < buffer.length; index += chunkLength) {

    }
  }

  /**
   * contains nav point data
   */
  private void extractBlock2(Wc1ModuleData data, byte[] buffer) {

    // nav points start at relative 0x1340, chunk length=0x4d (77 dez)
    int offset = 0x1340;
    int chunkSize = 77;
    int counter = 0;
    for (int index = offset; index < buffer.length; index += chunkSize) {
      Wc1NavPoint navPoint = new Wc1NavPoint();
      if (buffer[index] == (byte) 0x0) {
        continue;
      }
      String id = new String(Arrays.copyOfRange(buffer, index, index + 30));
      navPoint.setId(id.substring(0, id.indexOf("\0")));
      navPoint.setVisible(!id.startsWith("."));
      navPoint.setUnknown1(readerHelper.getShort(Arrays.copyOfRange(buffer, index + 30, index + 32)));

      byte[] xBytes = Arrays.copyOfRange(buffer, index + 32, index + 34);
      navPoint.setxPos(readerHelper.getShort(xBytes));
      navPoint.setUnknown2(readerHelper.getShort(new byte[] { buffer[index + 34], buffer[index + 35] }));
      byte[] yBytes = Arrays.copyOfRange(buffer, index + 36, index + 38);
      navPoint.setyPos(readerHelper.getShort(yBytes));
      navPoint.setInSystemJumpPoint(buffer[index + 38]);
      navPoint.setIsJumpPoint(buffer[index + 39]);

      List<Wc1NavPointManipulation> navManList = new ArrayList<>();
      for (int i = 0; i < 8; i += 2) {
        byte enableByte = buffer[index + 45 + i];
        if (enableByte == (byte) 0xff) {
          break;
        }
        Wc1NavPointManipulation npm = new Wc1NavPointManipulation();
        npm.setEnableNavPoint(enableByte == 1);
        npm.setNavPoint(buffer[index + 46 + i]);
        navManList.add(npm);
      }
      navPoint.setNavPointManipulations(navManList);

      List<Short> shipsToLoad = new ArrayList<>();
      for (int i = 0; i < 12; i += 2) {
        short shipToLoad = readerHelper.getShort(new byte[] { buffer[index + i + 53], buffer[index + i + 54] });
        if (shipToLoad == (short) 0xffff) {
          break;
        }
        shipsToLoad.add(shipToLoad);
      }
      navPoint.setShipsToLoad(shipsToLoad);

      System.out.println("0x" + Integer.toHexString(index).toUpperCase() + " - " + ++counter + navPoint.toString());
    }
  }

  private void extractThirdBlock(Wc1ModuleData result, byte[] copyOfRange, int blockOffset) {
    int start = 0x1000;

  }

  private void extractSizedBlock(Wc1ModuleData result, byte[] buffer, int startOffset, int chunkSize) {
    for (int index = 0; index < buffer.length; index += chunkSize) {
      int absOffset = startOffset + index;
      String raw = new String(Arrays.copyOfRange(buffer, index, index + chunkSize));
      raw = raw.substring(0, raw.indexOf("\0"));
      System.out.println("0x" + Integer.toHexString(index).toUpperCase() + " 0x" + Integer.toHexString(absOffset).toUpperCase() + " " + raw);
    }
  }

}
