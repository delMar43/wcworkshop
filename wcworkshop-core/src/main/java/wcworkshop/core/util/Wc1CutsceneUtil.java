package wcworkshop.core.util;

public class Wc1CutsceneUtil {

  private static final Wc1CutsceneUtil instance = new Wc1CutsceneUtil();

  private Wc1CutsceneUtil() {
  }

  public String getTextColor(byte color) {
    String result;
    switch (color) {
      case 0:
        result = "Light Blue";
        break;
      case 1:
        result = "Lavendar (Spirit)";
        break;
      case 2:
        result = "Royal Blue (Hunter)";
        break;
      case 3:
        result = "Red (Bossman)";
        break;
      case 4:
        result = "Cyan (Iceman)";
        break;
      case 5:
        result = "Peach (Angel)";
        break;
      case 6:
        result = "Blue (Paladin / Doomsday)";
        break;
      case 7:
        result = "Electric Green (Maniac)";
        break;
      case 8:
        result = "Brown (Knight)";
        break;
      case 9:
        result = "Yellow (Bluehair)";
        break;
      case 10:
        result = "Pea Green (Shotglass)";
        break;
      case 11:
        result = "Medium Light Gray (First screens)";
        break;
      case 12:
        result = "Medium Gray";
        break;
      case 13:
        result = "Very Light Gray";
        break;
      case 14:
        result = "Very Dark Gray";
        break;
      case 15:
        result = "Medium Dark Gray";
        break;
      case 16:
        result = "Medium Light Gray";
        break;
      case 17:
        result = "Medium Light Gray";
        break;
      case 18:
        result = "Black";
        break;
      default:
        result = "Unknown";
    }
    result += " (0x" + Integer.toHexString(color) + ")";
    return result;
  }

  public String getForeground(byte foreground) {
    String result;

    switch (foreground) {
      case 0:
        result = "Briefing crowd moving";
        break;
      case 1:
        result = "Briefing crowd still";
        break;
      case 2:
        result = "Halcyon at podium";
        break;
      case 3:
        result = "Halcyon pointing to the nav map";
        break;
      case 4:
        result = "Nav map closeup";
        break;
      case 5:
        result = "Briefing crowd standing up";
        break;
      case 6:
        result = "Empty room";
        break;
      case 7:
        result = "Mid-range nav map";
        break;
      case 8:
        result = "Empty room";
        break;
      case 9:
        result = "Half wall";
        break;
      case 10:
        result = "Camera pans right across back of pilots";
        break;
      case 11:
        result = "Camera holds on back of pilots";
        break;
      case 12:
      case 13:
      case 14:
      case 15:
        result = "Various starfield debris shots";
        break;
      case 16:
        result = "Camera holds?";
        break;
      case 17:
        result = "DIVIDE ERROR, GAME CRASHES!";
        break;
      case 18:
      case 19:
        result = "Blank";
        break;
      case 20:
        result = "Halcyon";
        break;
      case 21:
        result = "Spirit";
        break;
      case 22:
        result = "Hunter";
        break;
      case 23:
        result = "Bossman / Jazz";
        break;
      case 24:
        result = "Iceman";
        break;
      case 25:
        result = "Angel";
        break;
      case 26:
        result = "Paladin / Doomsday";
        break;
      case 27:
        result = "Maniac";
        break;
      case 28:
        result = "Knight";
        break;
      case 29:
        result = "Bluehair";
        break;
      case 30:
        result = "Shotglass";
        break;
      case (byte) 0xFE:
        result = "End conversation";
        break;
      case (byte) 0xFF:
        result = "Nobody";
        break;
      default:
        result = "Unknown";
    }
    result += " (0x" + Integer.toHexString(foreground) + ")";
    return result;
  }

  public String getBackground(byte background) {
    String result;
    switch (background) {
      case 0:
        result = "Briefing room wall";
        break;
      case 1:
        result = "Briefing room crowd";
        break;
      case 2:
        result = "Shotglass window";
        break;
      case 3:
        result = "Left bar seat";
        break;
      case 4:
        result = "Right bar seat";
        break;
      case 10:
        result = "Halcyon background during debriefing";
        break;
      case 11:
        result = "Pilot background during debriefing (hangar door)";
        break;
      case (byte) 0xFF:
        result = "Nothing";
        break;
      default:
        result = "Unknown";
    }

    result += " (0x" + Integer.toHexString(background) + ")";
    return result;
  }

  public static Wc1CutsceneUtil getInstance() {
    return instance;
  }
}
