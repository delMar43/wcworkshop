package wcworkshop.service;

import org.junit.Test;

import wcworkshop.core.service.Wc1CutsceneService;
import wcworkshop.core.service.Wc1Cutscenes;

public class Wc1CutsceneServiceTest {

  private Wc1CutsceneService service = Wc1CutsceneService.getInstance();

  @Test
  public void test() {
    Wc1Cutscenes cutscenes = service.createCutscenes("000");
    System.out.println("done");
  }
}
