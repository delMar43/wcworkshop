package wcworkshop.web.controller;

import org.springframework.stereotype.Controller;

import wcworkshop.core.reader.Wc1GameDataReader;

@Controller
public class AllContentsTreeController {

  private Wc1GameDataReader reader = Wc1GameDataReader.getInstance();

}
