package wcworkshop.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import wcworkshop.core.dto.Wc1Cutscene;
import wcworkshop.core.dto.Wc1CutsceneBackground;
import wcworkshop.core.dto.Wc1CutsceneForeground;
import wcworkshop.core.dto.Wc1CutsceneScreen;
import wcworkshop.core.dto.Wc1CutsceneTextColor;
import wcworkshop.core.service.Wc1CutsceneReadService;
import wcworkshop.core.service.Wc1CutsceneType;
import wcworkshop.core.service.Wc1CutsceneWriteService;
import wcworkshop.core.service.Wc1Cutscenes;
import wcworkshop.core.service.Wc1MissionCutscenes;
import wcworkshop.core.writer.WriterHelper;

public class Wc1CutsceneLifecycleServiceTest {

  private Wc1CutsceneReadService readService = Wc1CutsceneReadService.getInstance();
  private Wc1CutsceneWriteService writeService = Wc1CutsceneWriteService.getInstance();
  private WriterHelper writerHelper = WriterHelper.getInstance();

  @Test
  public void outputEqualsInput() {
    Wc1Cutscenes cutscenes = readService.loadCutscenes("000");

    Wc1MissionCutscenes missionCutscenes = cutscenes.getMissionCutscenes().get(0);

    Wc1Cutscene cutscene = new Wc1Cutscene();
    cutscene.setScreens(createShotglassScreens());
    missionCutscenes.getCutscenes().put(Wc1CutsceneType.SHOTGLASS, cutscene);

    cutscene = new Wc1Cutscene();
    cutscene.setScreens(createLeftScreens());
    missionCutscenes.getCutscenes().put(Wc1CutsceneType.LEFT_SEAT, cutscene);

    cutscene = new Wc1Cutscene();
    cutscene.setScreens(createRightScreens());
    missionCutscenes.getCutscenes().put(Wc1CutsceneType.RIGHT_SEAT, cutscene);

    System.out.println("-------------------------");

    byte[] generated = writeService.generateCutscenes(cutscenes);

    writerHelper.writeFile(generated, "D:/spiele/Wing Commander 1 and 2/WC/GAMEDAT/BRIEFING.000");

    System.out.println("done");
  }

  private List<Wc1CutsceneScreen> createShotglassScreens() {
    List<Wc1CutsceneScreen> result = new ArrayList<>();

    result.add(createScreen("Welcome, Wingnut!", Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.SHOTGLASS_WINDOW,
        Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("Now look at that.\nBeen talking the same lines for the last 23 years or so...",
        Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("... but finally, I'm able to tell some new stories.", Wc1CutsceneForeground.SHOTGLASS,
        Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("Thanks to the wingnuts over at www.wcnews.com, something is about to happen.",
        Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.PEA_GREEN, (short) 90));

    result.add(createScreen("They have been decoding all the game files for what must have been years now...",
        Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.PEA_GREEN, (short) 90));

    result.add(createScreen("... and now there's that www.wcworkshop.net thingy, which is about to make use of all that research.",
        Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.PEA_GREEN, (short) 120));

    result.add(createScreen("By the way, I have never ever been sitting on one of those comfortable seats.",
        Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("Do you mind me taking a seat?", Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.SHOTGLASS_WINDOW,
        Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("Make yourself at home.\nI'll take care of the bar meanwhile.", Wc1CutsceneForeground.PLAYER,
        Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.YELLOW));

    result.add(createScreen("", Wc1CutsceneForeground.END_CONVERSATION, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.BLUE));

    return result;
  }

  private List<Wc1CutsceneScreen> createLeftScreens() {
    List<Wc1CutsceneScreen> result = new ArrayList<>();

    result.add(createScreen("Aahhh, now that's what I call some decent seating-accomodation.", Wc1CutsceneForeground.SHOTGLASS,
        Wc1CutsceneBackground.LEFT_BAR_SEAT, Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("By the way, does that other background make me look older?", Wc1CutsceneForeground.SHOTGLASS,
        Wc1CutsceneBackground.LEFT_BAR_SEAT, Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("There must be some reason for Mr. Roberts having me stand behind that counter for so long...",
        Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.LEFT_BAR_SEAT, Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("Hey, Colonel!\nTake a seat!", Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.LEFT_BAR_SEAT,
        Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("Hello, Shotglass.\nWhy did nobody tell me, that the Tiger's Claw has a bar?", Wc1CutsceneForeground.HALCYON,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.LIGHT_BLUE));

    result.add(createScreen("Great you've finally found the time for a visit after all those years.", Wc1CutsceneForeground.SHOTGLASS,
        Wc1CutsceneBackground.LEFT_BAR_SEAT, Wc1CutsceneTextColor.PEA_GREEN));
    result.add(createScreen("Must have been years since our last briefing.", Wc1CutsceneForeground.SHOTGLASS,
        Wc1CutsceneBackground.LEFT_BAR_SEAT, Wc1CutsceneTextColor.PEA_GREEN));

    result.add(createScreen("Happy Hour, pilots! Drinks are only half the price!", Wc1CutsceneForeground.PLAYER,
        Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.YELLOW));

    result.add(createScreen("You're crazy or what?", Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.LEFT_BAR_SEAT,
        Wc1CutsceneTextColor.PEA_GREEN));
    result.add(createScreen("Let me get back on my place!", Wc1CutsceneForeground.SHOTGLASS, Wc1CutsceneBackground.LEFT_BAR_SEAT,
        Wc1CutsceneTextColor.PEA_GREEN));
    result.add(createScreen("Happy Hour?\nYay!", Wc1CutsceneForeground.MANIAC, Wc1CutsceneBackground.RIGHT_BAR_SEAT,
        Wc1CutsceneTextColor.ELECTRIC_GREEN));

    result.add(createScreen("", Wc1CutsceneForeground.END_CONVERSATION, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.BLUE));
    return result;
  }

  private List<Wc1CutsceneScreen> createRightScreens() {
    List<Wc1CutsceneScreen> result = new ArrayList<>();

    result.add(createScreen("Hello, smarty pants!", Wc1CutsceneForeground.ANGEL, Wc1CutsceneBackground.RIGHT_BAR_SEAT,
        Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("I hear you flew some other fighter craft all those years?", Wc1CutsceneForeground.ANGEL,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("Not only faster and better equipped than ours, but also with higher resolutions?",
        Wc1CutsceneForeground.ANGEL, Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("Even some craft from some year in the future and from different universes?", Wc1CutsceneForeground.ANGEL,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("But, are you also up to creating your own world?", Wc1CutsceneForeground.ANGEL,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("www.wcworkshop.net will be THE place to be, if you want to try.", Wc1CutsceneForeground.ANGEL,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("As you can see, some stuff is already working code-wise.", Wc1CutsceneForeground.ANGEL,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("Besides some fancy UI, all we still need is wingnuts with visions", Wc1CutsceneForeground.ANGEL,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("wcworkshop does not stand for Wing Commander Workshop, after all.", Wc1CutsceneForeground.ANGEL,
        Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("It's 'World Creator's Workshop'", Wc1CutsceneForeground.ANGEL, Wc1CutsceneBackground.RIGHT_BAR_SEAT,
        Wc1CutsceneTextColor.PEACH));
    result.add(createScreen("As Origin is not creating worlds anymore, we need to take care of that ourselves, no?",
        Wc1CutsceneForeground.ANGEL, Wc1CutsceneBackground.RIGHT_BAR_SEAT, Wc1CutsceneTextColor.PEACH));

    result.add(createScreen("", Wc1CutsceneForeground.END_CONVERSATION, Wc1CutsceneBackground.SHOTGLASS_WINDOW, Wc1CutsceneTextColor.BLUE));
    return result;
  }

  private Wc1CutsceneScreen createScreen(String text, Wc1CutsceneForeground foreground, Wc1CutsceneBackground background,
      Wc1CutsceneTextColor textColor) {
    return createScreen(text, foreground, background, textColor, (short) 150);
  }

  private Wc1CutsceneScreen createScreen(String text, Wc1CutsceneForeground foreground, Wc1CutsceneBackground background,
      Wc1CutsceneTextColor textColor, short duration) {
    String phonetic = "Bimorgameslikwincamandertoo";
    for (int i = 0; i < (text.length() / 45); ++i) {
      phonetic += phonetic;
    }

    return createScreen("", text, phonetic, "", duration, foreground, background, textColor);
  }

  private Wc1CutsceneScreen createScreen(String commands, String text, String phonetic, String facialExpressions, short unknown,
      Wc1CutsceneForeground foreground, Wc1CutsceneBackground background, Wc1CutsceneTextColor textColor) {

    Wc1CutsceneScreen result = new Wc1CutsceneScreen();
    //    result.setCommands(commands);
    result.setText(text);
    result.setPhonetic(phonetic);
    result.setFacialExpression(facialExpressions);
    result.setUnknown(unknown);
    result.setForeground(foreground.getValue());
    result.setBackground(background.getValue());
    result.setTextColor(textColor.getValue());

    return result;
  }

  private Wc1CutsceneScreen createScreen(String string, String string2, String string3, String string4, short s) {
    // TODO Auto-generated method stub
    return null;
  }
}
