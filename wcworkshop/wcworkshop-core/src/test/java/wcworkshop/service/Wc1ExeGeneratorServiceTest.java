package wcworkshop.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import wcworkshop.core.binary.Wc1ExeFile;
import wcworkshop.core.binary.Wc1ExeFileType;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import wcworkshop.core.service.Wc1ExeGeneratorService;
import binmap.BinaryReader;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1ExeGeneratorServiceTest {

  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private BinaryReader binaryReader = BinaryReader.getInstance();

  private Wc1ExeGeneratorService service = Wc1ExeGeneratorService.getInstance();

  @Test
  public void unchangedFileIsIdentical() throws FileNotFoundException, IOException {
    Wc1ExeFileType fileType = Wc1ExeFileType.DOS;
    Mapping mapping = mappingFactory.createMapping(fileType.getMapping() + ".mapping");
    byte[] referenceData = readerHelper.readFile(Configuration.getInstance().getResourcePath() + fileType.getExeName());
    Wc1ExeFile referenceFile = binaryReader.toJava(referenceData, mapping, Wc1ExeFile.class);

    byte[] generated = service.generateExe(referenceFile, fileType);

    Assert.assertArrayEquals(referenceData, generated);

    IOUtils.write(generated, new FileOutputStream("d:/unchanged_" + fileType.getExeName()));
  }

  @Test(expected = AssertionError.class)
  public void changedFileIsNotIdentical() throws FileNotFoundException, IOException {
    Wc1ExeFileType fileType = Wc1ExeFileType.DOS;
    Mapping mapping = mappingFactory.createMapping(fileType.getMapping() + ".mapping");
    byte[] referenceData = readerHelper.readFile(Configuration.getInstance().getResourcePath() + fileType.getExeName());
    Wc1ExeFile referenceFile = binaryReader.toJava(referenceData, mapping, Wc1ExeFile.class);

    referenceFile.setStartDay((short) 100);
    referenceFile.setIntroText("www.wcnews.com rules!\0HCl rules!\0The Workshop rules!\0");

    byte[] generated = service.generateExe(referenceFile, fileType);

    IOUtils.write(generated, new FileOutputStream("d:/changed_" + fileType.getExeName()));
    Assert.assertArrayEquals(referenceData, generated);
  }
}
