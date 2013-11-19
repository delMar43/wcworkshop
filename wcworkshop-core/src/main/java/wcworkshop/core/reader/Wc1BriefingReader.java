package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1BriefingData;
import wcworkshop.core.data.Wc1Cutscene;
import wcworkshop.core.data.Wc1CutsceneLine;
import wcworkshop.core.data.Wc1Data;
import wcworkshop.core.data.Wc1MissionSlot;

public class Wc1BriefingReader {

  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1BriefingData readBriefingFile(String path) {
    byte[] buffer = readerHelper.readFile(path);
    if (buffer == null) {
      return Wc1BriefingData.EMPTY;
    }

    Wc1BriefingData result = new Wc1BriefingData();
    result.setBuffer(buffer);
    readerHelper.extractFilesize(result, buffer);
    readerHelper.extractBlockOffsets(result, buffer);
    //    extractFuneralBlock(result, buffer);
    //    extractHalcyonBlock(result, buffer);
    //    extractMedalCeremonyBlock(result, buffer);
    extractMissionBlocks(result, buffer);

    return result;
  }

  private void extractFuneralBlock(Wc1BriefingData data, byte[] buffer) {
    byte[] funeralBuffer = extractBlock(data, buffer, 0);
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

  private byte[] extractBlock(Wc1Data data, byte[] buffer, int offsetIndex) {
    byte[] blockBuffer = Arrays.copyOfRange(buffer, data.getBlockOffset(offsetIndex), data.getBlockOffset(offsetIndex + 1));
    return blockBuffer;
  }

  private void extractHalcyonBlock(Wc1BriefingData data, byte[] buffer) {
    byte[] halcyonBuffer = extractBlock(data, buffer, 1);
    System.out.println("halcyonBuffer has " + halcyonBuffer.length + " bytes: " + Arrays.toString(halcyonBuffer));
  }

  private void extractMedalCeremonyBlock(Wc1BriefingData data, byte[] buffer) {
    byte[] medalCeremonyBuffer = extractBlock(data, buffer, 2);
    System.out.println("medalCeremonyBuffer has " + medalCeremonyBuffer.length + " bytes: " + Arrays.toString(medalCeremonyBuffer));
  }

  private List<Wc1MissionSlot> extractMissionBlocks(Wc1BriefingData data, byte[] buffer) {
    List<Wc1MissionSlot> missionSlots = new ArrayList<>();
    for (int blockIndex = 4; blockIndex < data.getNrBlockOffsets(); ++blockIndex) {
      int missionStartOffset = data.getBlockOffset(blockIndex);
      int missionEndOffset;
      if (blockIndex + 1 < data.getNrBlockOffsets()) {
        missionEndOffset = data.getBlockOffset(blockIndex + 1);
      } else {
        missionEndOffset = data.getFilesize();
      }
      byte[] missionBuffer = Arrays.copyOfRange(buffer, missionStartOffset, missionEndOffset);
      System.out.println("Mission " + (blockIndex - 3) + " starts at " + missionStartOffset);
      if (missionBuffer.length == 0) {
        continue;
      }
      Wc1MissionSlot missionSlot = extractMissionBlock(data, missionBuffer);
      missionSlots.add(missionSlot);
    }
    return missionSlots;
  }

  private Wc1MissionSlot extractMissionBlock(Wc1BriefingData data, byte[] missionBuffer) {
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
        line.setCommands(parts[partIndex + 1].getBytes());
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
}
