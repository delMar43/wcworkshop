package wcworkshop.core.service;

import wcworkshop.core.binary.Wc1ExeFile;
import wcworkshop.core.binary.Wc1ExeFileType;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.reader.ReaderHelper;
import binmap.BinaryReader;
import binmap.BinaryWriter;
import binmap.Mapping;
import binmap.MappingFactory;

public class Wc1ExeGeneratorService {
  private static final Wc1ExeGeneratorService instance = new Wc1ExeGeneratorService();

  private MappingFactory mappingFactory = MappingFactory.getInstance();
  private ReaderHelper readerHelper = ReaderHelper.getInstance();
  private BinaryReader binaryReader = BinaryReader.getInstance();
  private BinaryWriter binaryWriter = BinaryWriter.getInstance();

  private Wc1ExeGeneratorService() {
  }

  public byte[] generateExe(Wc1ExeFile fileData, Wc1ExeFileType type) {
    Mapping mapping = mappingFactory.createMapping(type.getMapping() + ".mapping");
    byte[] patternData = readerHelper.readFile(Configuration.getInstance().getResourcePath() + type.getExeName());
    Wc1ExeFile patternFile = binaryReader.toJava(patternData, mapping, Wc1ExeFile.class);

    fileData.setUnknown1(patternFile.getUnknown1());
    fileData.setUnknown2(patternFile.getUnknown2());
    fileData.setUnknown3(patternFile.getUnknown3());
    fileData.setUnknown4(patternFile.getUnknown4());

    byte[] newContents = binaryWriter.toBinary(fileData, mapping);

    return newContents;
  }

  public static Wc1ExeGeneratorService getInstance() {
    return instance;
  }
}
