package wcworkshop.service;

import org.junit.Test;

import wcworkshop.core.service.Wc1CutsceneReadService;
import wcworkshop.core.service.Wc1CutsceneWriteService;
import wcworkshop.core.service.Wc1Cutscenes;

public class Wc1CutsceneLifecycleServiceTest {

  private Wc1CutsceneReadService readService = Wc1CutsceneReadService.getInstance();
  private Wc1CutsceneWriteService writeService = Wc1CutsceneWriteService.getInstance();

  @Test
  public void outputEqualsInput() {
    Wc1Cutscenes cutscenes = readService.loadCutscenes("000");

    System.out.println("-------------------------");

    byte[] generated = writeService.generateCutscenes(cutscenes);

    System.out.println("done");
  }
}
