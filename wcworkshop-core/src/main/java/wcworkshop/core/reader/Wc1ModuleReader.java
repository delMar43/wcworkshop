package wcworkshop.core.reader;

import java.util.Arrays;

import wcworkshop.core.data.Wc1ModuleData;

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

    System.out.println("Block 2:");
    extractSizedBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(1), result.getBlockOffset(2)), result.getBlockOffset(1), 77);

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
