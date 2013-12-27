package wcworkshop.web.controller.editor;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Wc1CutsceneBackground;
import wcworkshop.core.dto.Wc1CutsceneForeground;
import wcworkshop.core.dto.Wc1CutsceneScreen;
import wcworkshop.core.dto.Wc1CutsceneTextColor;
import wcworkshop.core.service.Wc1CutsceneType;
import wcworkshop.web.command.CutsceneCommand;

@Controller
public class CutsceneEditorNewScreenController {

  @RequestMapping("/addScreen")
  public ModelAndView render(@RequestParam String projectId, @RequestParam String missionId, @RequestParam String cutsceneType,
      @RequestParam int nextScreen) {
    ModelAndView result = new ModelAndView("editors/cutsceneEditorLine");

    Wc1CutsceneType cutsceneTypeEnum = Wc1CutsceneType.valueOf(cutsceneType.toUpperCase());

    CutsceneCommand command = new CutsceneCommand();
    command.setProjectId(projectId);
    command.setMissionId(missionId);
    command.setCutsceneType(cutsceneTypeEnum.toString());

    Wc1CutsceneScreen screen = new Wc1CutsceneScreen();
    screen.setSequence(nextScreen + 1);
    screen.setUnknown((short) 60);
    Wc1CutsceneScreen[] screens = new Wc1CutsceneScreen[nextScreen + 1];
    screens[nextScreen] = screen;
    command.setScreens(Arrays.asList(screens));

    result.addObject("command", command);
    result.addObject("screenIndex", nextScreen);

    result.addObject("missionId", missionId);
    result.addObject("cutsceneType", cutsceneTypeEnum);
    result.addObject("projectId", projectId);
    result.addObject("foregrounds", Wc1CutsceneForeground.values());
    result.addObject("backgrounds", Wc1CutsceneBackground.values());
    result.addObject("textColors", Wc1CutsceneTextColor.values());
    result.addObject("cutsceneId", projectId + "_" + missionId + "_" + cutsceneType);
    return result;
  }
}
