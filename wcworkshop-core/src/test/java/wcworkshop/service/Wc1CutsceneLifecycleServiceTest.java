package wcworkshop.service;

import org.junit.Test;

import wcworkshop.core.service.Wc1CutsceneReadService;
import wcworkshop.core.service.Wc1CutsceneWriteService;
import wcworkshop.core.service.Wc1Cutscenes;
import wcworkshop.core.writer.WriterHelper;

public class Wc1CutsceneLifecycleServiceTest {

  private Wc1CutsceneReadService readService = Wc1CutsceneReadService.getInstance();
  private Wc1CutsceneWriteService writeService = Wc1CutsceneWriteService.getInstance();
  private WriterHelper writerHelper = WriterHelper.getInstance();

  @Test
  public void outputEqualsInput() {
    Wc1Cutscenes cutscenes = readService.loadCutscenes("000");

    System.out.println("-------------------------");

    byte[] generated = writeService.generateCutscenes(cutscenes);

    writerHelper.writeFile(generated, "d:/generated.000");

    System.out.println("done");
  }

}
