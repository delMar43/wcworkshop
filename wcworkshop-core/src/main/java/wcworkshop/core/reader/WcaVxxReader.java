package wcworkshop.core.reader;

import java.util.Arrays;
import java.util.List;

import wcworkshop.core.workdata.Block;

public class WcaVxxReader {
  private static final WcaVxxReader instance = new WcaVxxReader();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  private WcaVxxReader() {
  }

  public void read(String path) {
    byte[] file = readerHelper.readFile(path);

    List<Block> blocks = readerHelper.extractBlocks(file);
    System.out.println(Arrays.toString(blocks.toArray()));

    for (int blockIndex = 0; blockIndex < blocks.size(); ++blockIndex) {
      Block block = blocks.get(blockIndex);
      int start = block.getOffset();
      int end;
      if ((blockIndex + 1) == blocks.size()) {
        end = file.length;
      } else {
        end = blocks.get(blockIndex + 1).getOffset();
      }
      byte[] blockData = Arrays.copyOfRange(file, start, end);

      System.out.println(blockIndex + ": starts at " + start + ", ends at " + end);
      extractBlockDataWithBlocksizeAtBeginning(blockData, start, file.length);

    }
  }

  /**
  From memory, it is has a starting codeword length of<br/>
  9-bits, with codeword 256 being a dictionary reset, and 257 being a<br/>
  stop code. The dictionary itself begins at code 258 and the dictionary<br/>
  can grow until the codeword length is 12-bits long<br/>
   */
  private void extractBlockDataWithBlocksizeAtBeginning(byte[] blockData, int start, int filesize) {
    // System.out.println("compressed: " + readerHelper.byteArrayToHexString(blockData));
    int blockLength = readerHelper.getInteger(Arrays.copyOfRange(blockData, 0, 4));
    System.out.println(" block length: " + blockLength);

    byte[] blockOffsets = Arrays.copyOfRange(blockData, 4, blockData.length);

    List<Integer> secondaryTable = readerHelper.extractSecondaryTable(blockOffsets);
    System.out.println(" secondary @" + start + ": " + Arrays.toString(secondaryTable.toArray()));

    for (int offsetIndex = 0; offsetIndex < secondaryTable.size(); ++offsetIndex) {
      int offset = secondaryTable.get(offsetIndex);
      if (offset >= filesize) {
        return;
      }
      int end;
      if ((offsetIndex + 1) == secondaryTable.size()) {
        end = blockData.length;
      } else {
        end = secondaryTable.get(offsetIndex + 1);
      }
      if (end >= filesize) {
        end = filesize;
      }
      byte[] input = Arrays.copyOfRange(blockData, offset, end);
      System.out.println("  " + readerHelper.byteArrayToHexString(input));
    }

    /*
     * blockData = Arrays.copyOfRange(blockData, 4, blockData.length); List<Integer> secondaryTable = readerHelper.extractSecondaryTable(blockData); int
     * blockLength = secondaryTable.get(0); System.out.println(" blocklength: " + blockLength); System.out.println(" secondary: " +
     * Arrays.toString(secondaryTable.toArray()));
     */
  }

  public static WcaVxxReader getInstance() {
    return instance;
  }
}
