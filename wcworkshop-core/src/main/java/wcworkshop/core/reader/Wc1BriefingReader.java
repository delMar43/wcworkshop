package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1BriefingData;
import wcworkshop.core.data.Wc1CutsceneScreen;
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
    extractFuneralBlock(result, buffer);
    extractHalcyonBlock(result, buffer);
    extractMedalCeremonyBlock(result, buffer);
    extractMissionBlocks(result, buffer);

    return result;
  }

  private void extractFuneralBlock(Wc1BriefingData data, byte[] buffer) {
    byte[] funeralBuffer = extractBlock(data, buffer, 0);
    System.out.println("funeralBuffer has " + funeralBuffer.length + " bytes: " + Arrays.toString(funeralBuffer));
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

    result.setBriefingCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 0)); //briefing
    result.setDebriefingCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 2)); //debriefing
    result.setShotglassCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 4)); //shotglass
    result.setLeftCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 6)); //right bar seat
    result.setRightCutscene(extractCutscene(missionBuffer, secondaryTable, (byte) 8)); //left bar seat

    return result;
  }

  private Wc1CutsceneScreen extractCutscene(byte[] missionBuffer, List<Integer> secondaryTable, byte firstOffset) {
    Wc1CutsceneScreen screen = new Wc1CutsceneScreen();

    byte[] entryBuffer = extractEntryBuffer(missionBuffer, secondaryTable, firstOffset);
    screen.setForeground(entryBuffer[0]);
    screen.setTextColor(entryBuffer[1]);
    screen.setBackground(entryBuffer[2]);
    screen.setUnknown1(entryBuffer[3]);
    screen.setUnknown2(entryBuffer[4]);
    screen.setConditionOffset(readerHelper.getShort(new byte[] { entryBuffer[5], entryBuffer[6] }));
    screen.setTextOffset(readerHelper.getShort(new byte[] { entryBuffer[7], entryBuffer[8] }));
    screen.setPhoneticOffset(readerHelper.getShort(new byte[] { entryBuffer[9], entryBuffer[10] }));
    screen.setTalkOffset(readerHelper.getShort(new byte[] { entryBuffer[11], entryBuffer[12] }));

    entryBuffer = extractEntryBuffer(missionBuffer, secondaryTable, firstOffset + 1);
    if (entryBuffer.length > screen.getTextOffset()) {
      screen.setCondition(new String(Arrays.copyOfRange(entryBuffer, screen.getConditionOffset(), screen.getTextOffset())));
      screen.setText(new String(Arrays.copyOfRange(entryBuffer, screen.getTextOffset(), screen.getPhoneticOffset())));
      screen.setPhonetic(new String(Arrays.copyOfRange(entryBuffer, screen.getPhoneticOffset(), screen.getTalkOffset())));
      screen.setTalk(new String(Arrays.copyOfRange(entryBuffer, screen.getTalkOffset(), entryBuffer.length)));
    }

    System.out.println("FG: " + screen.getForeground() + ", TC: " + screen.getTextColor() + ", BG: " + screen.getBackground()
        + ", Condition: " + screen.getCondition() + ", Headline: " + screen.getText() + ", Text: " + screen.getTalk() + ", Phonetic: "
        + screen.getPhonetic());

    return screen;
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
