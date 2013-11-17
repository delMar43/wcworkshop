package wcworkshop.core.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import wcworkshop.core.data.Wc1Data;

public class ReaderHelper {
  private static final ReaderHelper INSTANCE = new ReaderHelper();

  private ReaderHelper() {
  }

  public byte[] readFile(String path) {
    byte[] buffer;
    try {
      buffer = IOUtils.toByteArray(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      buffer = null;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      buffer = null;
    }
    return buffer;
  }

  public void extractFilesize(Wc1Data data, byte[] buffer) {
    byte[] bytes = Arrays.copyOfRange(buffer, 0, 4);
    int filesize = getInteger(bytes);
    data.setFilesize(filesize);
  }

  public int getInteger(byte[] bytes) {
    return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
  }

  public short getShort(byte[] bytes) {
    return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
  }

  public void extractBlockOffsets(Wc1Data data, byte[] buffer) {
    List<Integer> blockOffsets = new ArrayList<>();
    int offset = 4;
    do {
      byte[] bytes = Arrays.copyOfRange(buffer, offset, offset + 4);
      bytes[3] = 0; //always 01 in wc1 file, unknown
      blockOffsets.add(getInteger(bytes));
      offset += 4;
    } while (offset < blockOffsets.get(0));

    data.setBlockOffsets(blockOffsets);
  }

  public List<Integer> extractSecondaryTable(byte[] buffer) {
    List<Integer> blockOffsets = new ArrayList<>();
    int offset = 0;
    do {
      byte[] bytes = Arrays.copyOfRange(buffer, offset, offset + 4);
      bytes[3] = 0; //always 01 in wc1 file, unknown
      int blockOffset = getInteger(bytes);
      System.out.println("   " + blockOffset);
      blockOffsets.add(blockOffset);
      offset += 4;
    } while (offset < blockOffsets.get(0));

    return blockOffsets;
  }

  public static ReaderHelper getInstance() {
    return INSTANCE;
  }
}
