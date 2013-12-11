package wcworkshop.service;

import org.junit.Test;

import wcworkshop.core.service.Wc1CutsceneService;
import wcworkshop.core.service.Wc1Cutscenes;

public class Wc1CutsceneServiceTest {

  private Wc1CutsceneService service = new Wc1CutsceneService();

  @Test
  public void test() {
    Wc1Cutscenes cutscenes = service.createCutscenes();
    System.out.println("done");
  }
}
