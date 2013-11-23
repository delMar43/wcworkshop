package wcworkshop.core.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriterHelper {
  private static final Logger logger = LoggerFactory.getLogger(WriterHelper.class);
  private static final WriterHelper instance = new WriterHelper();

  private WriterHelper() {
  }

  public void backup(String filename) {
    try {
      FileUtils.copyFile(new File(filename), new File(filename + ".bak"));
    } catch (IOException e) {
      logger.error("Error while trying to create backup of file {}: {}", filename, e.getMessage());
    }
  }

  public void writeFile(byte[] data, String filename) {
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(filename);
      IOUtils.write(data, fos);
      fos.flush();
    } catch (Exception e) {
      logger.error("Error while trying to write file {}: {}", filename, e.getMessage());
    } finally {
      IOUtils.closeQuietly(fos);
    }
  }

  public void writeToBinary(String data, byte[] target, int offset, int length) {
    byte[] name = data.getBytes();
    byte[] toWrite = new byte[length];
    if (name.length < length) {
      int index = 0;
      for (byte b : name) {
        toWrite[index++] = b;
      }
      Arrays.fill(toWrite, data.length(), toWrite.length, (byte) 0);
    }

    int sourceIndex = 0;
    for (int targetIndex = offset; targetIndex < offset + length; ++targetIndex) {
      target[targetIndex] = toWrite[sourceIndex++];
    }
  }

  public static WriterHelper getInstance() {
    return instance;
  }
}
