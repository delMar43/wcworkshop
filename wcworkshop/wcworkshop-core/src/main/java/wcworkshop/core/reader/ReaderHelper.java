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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wcworkshop.core.config.Configuration;
import wcworkshop.core.workdata.Block;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class ReaderHelper {
  private static final Logger logger = LoggerFactory.getLogger(ReaderHelper.class);
  private static final ReaderHelper INSTANCE = new ReaderHelper();

  private MappingFactory mappingFactory = MappingFactory.getInstance();

  private ReaderHelper() {
  }

  public byte[] readFile(String path) {
    byte[] buffer;
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(path);
      buffer = IOUtils.toByteArray(fis);
    } catch (FileNotFoundException e) {
      logger.error("Unable to find file: {}", path);
      buffer = null;
    } catch (IOException e) {
      logger.error("Unable to load file {} because of {}", path, e.getMessage());
      buffer = null;
    } finally {
      IOUtils.closeQuietly(fis);
    }
    return buffer;
  }

  public int extractFilesize(byte[] buffer) {
    byte[] bytes = Arrays.copyOfRange(buffer, 0, 4);
    int filesize = getInteger(bytes);
    return filesize;
  }

  public int getInteger(byte[] bytes) {
    return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
  }

  public int getInteger(byte[] bytes, int offset, int length) {
    byte[] data = Arrays.copyOfRange(bytes, offset, offset + 4);
    if (length == 3) {
      data[3] = 0;
    }
    return getInteger(data);
  }

  public short getShort(byte[] bytes) {
    return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
  }

  public short getShort(byte[] buffer, int offset) {
    return getShort(new byte[] { buffer[offset], buffer[offset + 1] });
  }

  public List<Integer> extractBlockOffsets(byte[] buffer) {
    List<Integer> blockOffsets = new ArrayList<>();
    int offset = 4;
    do {
      byte[] bytes = Arrays.copyOfRange(buffer, offset, offset + 4);
      bytes[3] = 0; //
      blockOffsets.add(getInteger(bytes));
      offset += 4;
    } while (offset < blockOffsets.get(0));

    return blockOffsets;
  }

  public List<Block> extractBlocks(byte[] buffer) {
    List<Block> blockOffsets = new ArrayList<>();
    int offset = 4;
    do {
      byte[] bytes = Arrays.copyOfRange(buffer, offset, offset + 4);
      boolean compressed = bytes[3] == (byte) 1;
      bytes[3] = 0; //
      blockOffsets.add(new Block(getInteger(bytes), compressed));
      offset += 4;
    } while (offset < blockOffsets.get(0).getOffset());

    return blockOffsets;
  }

  public List<Integer> extractSecondaryTable(byte[] buffer) {
    List<Integer> blockOffsets = new ArrayList<>();
    int offset = 0;
    do {
      byte[] bytes = Arrays.copyOfRange(buffer, offset, offset + 4);
      // if (bytes[3] == 1) { //01 in wc1 means compressed, e0 means uncompressed
      // throw new RuntimeException(
      // "This file contains compressed data. Not yet able to uncompress it. Please use uncompressed data (eg wc1 kilrathi sage pc gamer coverdisk version available for download at wcnews.com)");
      // }
      bytes[3] = 0;
      int blockOffset = getInteger(bytes);
      // System.out.println("   " + blockOffset);
      blockOffsets.add(blockOffset);
      offset += 4;
    } while (offset < blockOffsets.get(0));

    return blockOffsets;
  }

  public String byteArrayToHexString(byte[] input) {
    if (input == null) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    for (byte b : input) {
      result.append("0x" + Integer.toHexString(b) + " ");
    }
    return result.toString();
  }

  public String getString(byte[] savegameBlock, int offset, int length) {
    String result = new String(savegameBlock, offset, length);
    if (result.contains("\0")) {
      result = result.substring(0, result.indexOf("\0"));
    }
    return result;
  }

  public <T> T read(String binaryName, String mappingName, Class<T> clazz) {
    Mapping mapper = mappingFactory.createMapping(mappingName);
    byte[] data = readFile(Configuration.getInstance().getResourcePath() + binaryName);
    T file = BinaryReader.getInstance().toJava(data, mapper, clazz);

    return file;
  }

  public static ReaderHelper getInstance() {
    return INSTANCE;
  }

}
