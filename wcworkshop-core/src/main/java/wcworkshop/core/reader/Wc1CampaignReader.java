package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.data.Wc1CampMedal;
import wcworkshop.core.data.Wc1CampPilot;
import wcworkshop.core.data.Wc1MissionSlot;
import wcworkshop.core.data.Wc1SeriesSlot;

public class Wc1CampaignReader {

  private static final byte MAX_MISSIONS_PER_SLOT = 4;

  private ReaderHelper readerHelper = ReaderHelper.getInstance();

  public Wc1CampData readCampaignFile(String path) {
    byte[] buffer = readerHelper.readFile(path);
    if (buffer == null) {
      return Wc1CampData.EMPTY;
    }

    Wc1CampData result = new Wc1CampData();
    readerHelper.extractFilesize(result, buffer);
    readerHelper.extractBlockOffsets(result, buffer);
    extractFirstBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(0), result.getBlockOffset(1)));
    extractSecondBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(1), result.getBlockOffset(2)));
    extractThirdBlock(result, Arrays.copyOfRange(buffer, result.getBlockOffset(2), result.getFilesize()));

    return result;
  }

  private void extractFirstBlock(Wc1CampData data, byte[] buffer) {
    System.out.println("first block starts at " + data.getBlockOffset(0));

    List<byte[]> firstBlock = new ArrayList<>();
    int blockSize = 8;
    int counter = 0;
    for (int index = 0; index < buffer.length; index += blockSize) {
      byte[] byteArray = Arrays.copyOfRange(buffer, index, index + blockSize);
      System.out.println(++counter + ": " + Arrays.toString(byteArray));
      firstBlock.add(byteArray);
    }
    data.setFirstBlock(firstBlock);
  }

  private void extractSecondBlock(Wc1CampData result, byte[] buffer) {
    System.out.println("second block starts at " + result.getBlockOffset(1) + " and is " + buffer.length + " bytes long");
    // 90 bytes per series

    List<Wc1SeriesSlot> seriesSlots = new ArrayList<>();

    int startIndex = 0;
    int counter = 0;
    do {
      System.out.print("Series " + (++counter) + ": ");
      seriesSlots.add(extractSeriesAndMissions(Arrays.copyOfRange(buffer, startIndex, startIndex + 90)));
      startIndex += 90;
      System.out.println("-----");
    } while (startIndex <= buffer.length - 90);

    result.setSeriesSlots(seriesSlots);
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
    System.out.println(series.toString());

    // mission-data, 20 bytes per mission-slot
    List<Wc1MissionSlot> missionSlots = new ArrayList<>();
    for (int missionIndex = 0; missionIndex < MAX_MISSIONS_PER_SLOT; ++missionIndex) {
      System.out.print(" Mission " + (missionIndex + 1) + ": ");
      int offset = missionIndex * 20 + 10;
      Wc1MissionSlot slot = new Wc1MissionSlot();
      slot.setMedal(readerHelper.getShort(new byte[] { buffer[offset], buffer[offset + 1] }));
      slot.setMedalKillPoints(readerHelper.getShort(new byte[] { buffer[offset + 2], buffer[offset + 3] }));
      slot.setObjectiveVictoryPoints(Arrays.copyOfRange(buffer, offset + 4, offset + 20));

      System.out.println("medal: " + Wc1CampMedal.getByValue(slot.getMedal()) + ", medal killpoints: " + slot.getMedalKillPoints()
          + ", victoryPoints: " + Arrays.toString(slot.getObjectiveVictoryPoints()));

      missionSlots.add(slot);
    }

    series.setMissionSlots(missionSlots);

    return series;
  }

  private void extractThirdBlock(Wc1CampData result, byte[] buffer) {
    System.out.println("third block starts at " + result.getBlockOffset(2));

    int bufferIndex = 0;
    int counter = 0;
    for (Wc1SeriesSlot series : result.getSeriesSlots()) {
      for (int missionIndex = 0; missionIndex < MAX_MISSIONS_PER_SLOT; ++missionIndex) {
        Wc1MissionSlot missionSlot = series.getMissionSlot(missionIndex);
        extractMissionSlot(missionSlot, buffer, bufferIndex);
        System.out.println(" Mission " + ++counter + " left seat: " + Wc1CampPilot.getByValue(missionSlot.getLeftSeat()) + ", right seat: "
            + Wc1CampPilot.getByValue(missionSlot.getRightSeat()));
        bufferIndex += 2;
      }
    }
  }

  private void extractMissionSlot(Wc1MissionSlot data, byte[] buffer, int firstOffset) {
    data.setLeftSeat(buffer[firstOffset]);
    data.setRightSeat(buffer[firstOffset + 1]);
  }

}
