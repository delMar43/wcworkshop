package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.data.Wc1ConversationPartners;
import wcworkshop.core.data.Wc1MissionSlot;
import wcworkshop.core.data.Wc1SeriesSlot;

public class Wc1CampaignReader {
  private static final Wc1CampaignReader instance = new Wc1CampaignReader();

  private static final byte MAX_MISSIONS_PER_SLOT = 4;

  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1CampData readCampaignFile(String path) {
    byte[] buffer = readerHelper.readFile(path);
    if (buffer == null) {
      return Wc1CampData.EMPTY;
    }

    Wc1CampData result = new Wc1CampData();
    int filesize = readerHelper.extractFilesize(buffer);
    List<Integer> blockOffsets = readerHelper.extractBlockOffsets(buffer);
    result.setFirstBlock(extractFirstBlock(Arrays.copyOfRange(buffer, blockOffsets.get(0), blockOffsets.get(1)), blockOffsets));
    result.setSeriesSlots(extractSecondBlock(Arrays.copyOfRange(buffer, blockOffsets.get(1), blockOffsets.get(2)), blockOffsets));
    result.setConversationPartners(extractThirdBlock(buffer, blockOffsets, filesize));

    return result;
  }

  private List<byte[]> extractFirstBlock(byte[] buffer, List<Integer> blockOffsets) {
    //    System.out.println("first block starts at " + blockOffsets.get(0));

    List<byte[]> firstBlock = new ArrayList<>();
    int blockSize = 8;
    int counter = 0;
    for (int index = 0; index < buffer.length; index += blockSize) {
      byte[] byteArray = Arrays.copyOfRange(buffer, index, index + blockSize);
      //      System.out.println(++counter + ": " + Arrays.toString(byteArray));
      firstBlock.add(byteArray);
    }
    return firstBlock;
  }

  private List<Wc1SeriesSlot> extractSecondBlock(byte[] buffer, List<Integer> blockOffsets) {
    //    System.out.println("second block starts at " + blockOffsets.get(1) + " and is " + buffer.length + " bytes long");
    // 90 bytes per series

    List<Wc1SeriesSlot> seriesSlots = new ArrayList<>();

    int startIndex = 0;
    int counter = 0;
    do {
      //      System.out.print("Series " + (++counter) + ": ");
      seriesSlots.add(extractSeriesAndMissions(Arrays.copyOfRange(buffer, startIndex, startIndex + 90)));
      startIndex += 90;
      //      System.out.println("-----");
    } while (startIndex <= buffer.length - 90);

    return seriesSlots;
  }

  private Wc1SeriesSlot extractSeriesAndMissions(byte[] buffer) {
    // 10 bytes series data
    Wc1SeriesSlot series = new Wc1SeriesSlot();
    series.setWingman(readerHelper.getShort(new byte[] { buffer[0], buffer[1] }));
    series.setNrOfMissions(buffer[2]);
    series.setVictoryPoints(readerHelper.getShort(new byte[] { buffer[3], buffer[4] }));
    series.setMissionTreeLevel(buffer[5]);
    series.setVictoryDestination(buffer[6]);
    series.setVictoryShip(buffer[7]);
    series.setLossDestination(buffer[8]);
    series.setLossShip(buffer[9]);
    //    System.out.println(series.toString());

    // mission-data, 20 bytes per mission-slot
    List<Wc1MissionSlot> missionSlots = new ArrayList<>();
    for (int missionIndex = 0; missionIndex < MAX_MISSIONS_PER_SLOT; ++missionIndex) {
      //      System.out.print(" Mission " + (missionIndex + 1) + ": ");
      int offset = missionIndex * 20 + 10;
      Wc1MissionSlot slot = new Wc1MissionSlot();
      slot.setMedal(readerHelper.getShort(new byte[] { buffer[offset], buffer[offset + 1] }));
      slot.setMedalKillPoints(readerHelper.getShort(new byte[] { buffer[offset + 2], buffer[offset + 3] }));
      slot.setObjectiveVictoryPoints(Arrays.copyOfRange(buffer, offset + 4, offset + 20));

      //      System.out.println("medal: " + Wc1CampMedal.getByValue(slot.getMedal()) + ", medal killpoints: " + slot.getMedalKillPoints()
      //          + ", victoryPoints: " + Arrays.toString(slot.getObjectiveVictoryPoints()));

      missionSlots.add(slot);
    }

    series.setMissionSlots(missionSlots);

    return series;
  }

  private List<Wc1ConversationPartners> extractThirdBlock(byte[] allData, List<Integer> blockOffsets, int filesize) {
    byte[] buffer = Arrays.copyOfRange(allData, blockOffsets.get(2), filesize);

    List<Wc1ConversationPartners> result = new ArrayList<>();
    int bufferIndex = 0;
    while (bufferIndex + 1 < buffer.length) {
      Wc1ConversationPartners partners = extractConversationPartners(buffer, bufferIndex);
      result.add(partners);
      bufferIndex += 2;
    }
    return result;
  }

  private Wc1ConversationPartners extractConversationPartners(byte[] buffer, int firstOffset) {
    return new Wc1ConversationPartners(buffer[firstOffset], buffer[firstOffset + 1]);
  }

  public static Wc1CampaignReader getInstance() {
    return instance;
  }
}
