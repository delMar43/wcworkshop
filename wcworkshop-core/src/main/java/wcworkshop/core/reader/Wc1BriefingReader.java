package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1BriefingData;
import wcworkshop.core.data.Wc1Cutscene;
import wcworkshop.core.data.Wc1CutsceneLine;
import wcworkshop.core.data.Wc1MissionSlot;

public class Wc1BriefingReader {
  private static final Wc1BriefingReader instance = new Wc1BriefingReader();

  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1BriefingData readBriefingFile(String path) {
    byte[] buffer = readerHelper.readFile(path);
    if (buffer == null) {
      return Wc1BriefingData.EMPTY;
    }

    Wc1BriefingData result = new Wc1BriefingData();
    int filesize = readerHelper.extractFilesize(buffer);
    List<Integer> blockOffsets = readerHelper.extractBlockOffsets(buffer);
    //    extractFuneralBlock(result, buffer);
    //    extractHalcyonBlock(result, buffer);
    //    extractMedalCeremonyBlock(result, buffer);
    result.setMissionSlots(extractMissionSlots(buffer, blockOffsets, filesize));

    return result;
  }

  private void extractFuneralBlock(byte[] buffer, List<Integer> blockOffsets) {
    byte[] funeralBuffer = extractBlock(buffer, blockOffsets, 0);
    List<Integer> offsets = readerHelper.extractSecondaryTable(funeralBuffer);
    System.out.println("funeralBuffer has " + funeralBuffer.length + " bytes. offsets: " + Arrays.toString(offsets.toArray()));

    int blockIndex = 0;
    for (Integer startOffset : offsets) {
      int endOffset;
      if (blockIndex + 1 < offsets.size()) {
        endOffset = offsets.get(blockIndex + 1);
      } else {
        endOffset = funeralBuffer.length;
      }
      System.out.println("     from " + startOffset + " to " + endOffset);

      byte[] block = Arrays.copyOfRange(funeralBuffer, startOffset, endOffset);
      System.out.println("  " + new String(block));
      ++blockIndex;
    }
  }

  private byte[] extractBlock(byte[] buffer, List<Integer> blockOffsets, int offsetIndex) {
    byte[] blockBuffer = Arrays.copyOfRange(buffer, blockOffsets.get(offsetIndex), blockOffsets.get(offsetIndex + 1));
    return blockBuffer;
  }

  private void extractHalcyonBlock(byte[] buffer, List<Integer> blockOffsets) {
    byte[] halcyonBuffer = extractBlock(buffer, blockOffsets, 1);
    System.out.println("halcyonBuffer has " + halcyonBuffer.length + " bytes: " + Arrays.toString(halcyonBuffer));
  }

  private void extractMedalCeremonyBlock(byte[] buffer, List<Integer> blockOffsets) {
    byte[] medalCeremonyBuffer = extractBlock(buffer, blockOffsets, 2);
    System.out.println("medalCeremonyBuffer has " + medalCeremonyBuffer.length + " bytes: " + Arrays.toString(medalCeremonyBuffer));
  }

  private List<Wc1MissionSlot> extractMissionSlots(byte[] buffer, List<Integer> blockOffsets, int filesize) {
    List<Wc1MissionSlot> missionSlots = new ArrayList<>();
    for (int blockIndex = 4; blockIndex < blockOffsets.size(); ++blockIndex) {
      int missionStartOffset = blockOffsets.get(blockIndex);
      int missionEndOffset;
      if (blockIndex + 1 < blockOffsets.size()) {
        missionEndOffset = blockOffsets.get(blockIndex + 1);
      } else {
        missionEndOffset = filesize;
      }
      byte[] missionBuffer = Arrays.copyOfRange(buffer, missionStartOffset, missionEndOffset);
      System.out.println("Mission " + (blockIndex - 3) + " starts at " + missionStartOffset);

      Wc1MissionSlot missionSlot;
      if (missionBuffer.length > 0) {
        missionSlot = extractMissionBlock(missionBuffer);
      } else {
        missionSlot = Wc1MissionSlot.EMPTY;
      }
      missionSlots.add(missionSlot);
    }
    return missionSlots;
  }

  private Wc1MissionSlot extractMissionBlock(byte[] missionBuffer) {
    Wc1MissionSlot result = new Wc1MissionSlot();

    List<Integer> secondaryTable = readerHelper.extractSecondaryTable(missionBuffer);

    System.out.println("Briefing");
    result.setBriefingCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 0)); //briefing
    System.out.println("Debriefing");
    result.setDebriefingCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 2)); //debriefing
    System.out.println("Shotglass");
    result.setShotglassCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 4)); //shotglass
    System.out.println("Left Bar Seat");
    result.setLeftCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 6)); //right bar seat
    System.out.println("Right Bar Seat");
    result.setRightCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 8)); //left bar seat

    return result;
  }

  private Wc1Cutscene extractCutscene(byte[] missionBuffer, List<Integer> secondaryTable, byte firstOffset) {
    Wc1Cutscene scene = new Wc1Cutscene();

    byte[] entryBuffer = extractEntryBuffer(missionBuffer, secondaryTable, firstOffset);
    scene.setForeground(entryBuffer[0]);
    scene.setTextColor(entryBuffer[1]);
    scene.setBackground(entryBuffer[2]);
    scene.setUnknown1(entryBuffer[3]);
    scene.setUnknown2(entryBuffer[4]);
    scene.setConditionOffset(readerHelper.getShort(new byte[] { entryBuffer[5], entryBuffer[6] }));
    scene.setFirstLineOffset(readerHelper.getShort(new byte[] { entryBuffer[7], entryBuffer[8] }));
    scene.setFirstLinePhoneticOffset(readerHelper.getShort(new byte[] { entryBuffer[9], entryBuffer[10] }));
    scene.setTalkOffset(readerHelper.getShort(new byte[] { entryBuffer[11], entryBuffer[12] }));

    entryBuffer = extractEntryBuffer(missionBuffer, secondaryTable, firstOffset + 1);
    if (entryBuffer.length > scene.getFirstLineOffset()) {
      scene.setCondition(Arrays.copyOfRange(entryBuffer, scene.getConditionOffset(), scene.getFirstLineOffset()));
      scene.setFirstLine(new String(Arrays.copyOfRange(entryBuffer, scene.getFirstLineOffset(), scene.getFirstLinePhoneticOffset() - 1)));
      scene.setFirstLinePhonetic(new String(Arrays.copyOfRange(entryBuffer, scene.getFirstLinePhoneticOffset(), scene.getTalkOffset())));
      String talk = new String(Arrays.copyOfRange(entryBuffer, scene.getTalkOffset(), entryBuffer.length));
      List<Wc1CutsceneLine> cutsceneLines = extractCutsceneLines(talk);
      scene.setCutsceneLines(cutsceneLines);
    }

    System.out.println(scene.toString());

    return scene;
  }

  private List<Wc1CutsceneLine> extractCutsceneLines(String source) {
    List<Wc1CutsceneLine> result = new ArrayList<>();

    String[] parts = source.split("\0");
    for (int partIndex = 0; partIndex < parts.length; partIndex += 4) {
      Wc1CutsceneLine line = new Wc1CutsceneLine();

      line.setFacialExpressions(parts[partIndex]);
      if (partIndex + 1 < parts.length) {
        //        List<Wc1CutsceneCommand> commands = new ArrayList<>();
        //        for (byte command : parts[partIndex + 1].getBytes()) {
        //          Wc1CutsceneCommand cmd = Wc1CutsceneCommand.getByValue(command);
        //          if (cmd != null) {
        //            commands.add(cmd);
        //          }
        //        }
        line.setCommandBytes(parts[partIndex + 1].getBytes());
        //        line.setCommands(commands);
      }
      if (partIndex + 2 < parts.length) {
        line.setText(parts[partIndex + 2]);
      }
      if (partIndex + 3 < parts.length) {
        line.setPhonetic(parts[partIndex + 3]);
      }

      result.add(line);
    }

    return result;
  }

  private byte[] extractEntryBuffer(byte[] missionBuffer, List<Integer> secondaryTable, int offsetIndex) {
    int startOffset = secondaryTable.get(offsetIndex);
    int stopOffset;
    if (offsetIndex + 1 < secondaryTable.size()) {
      stopOffset = secondaryTable.get(offsetIndex + 1);
    } else {
      stopOffset = missionBuffer.length + 1;
    }
    byte[] entryBuffer = Arrays.copyOfRange(missionBuffer, startOffset, stopOffset);
    return entryBuffer;
  }

  public static Wc1BriefingReader getInstance() {
    return instance;
  }
}
