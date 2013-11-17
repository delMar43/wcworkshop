package wcworkshop.core.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wcworkshop.core.data.Wc1CampData;
import wcworkshop.core.data.Wc1MissionSlot;
import wcworkshop.core.data.Wc1SeriesSlot;

public class Wc1CampaignReader {

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

  private void extractFirstBlock(Wc1CampData result, byte[] buffer) {
    //TODO only unknown data. store it somehow
  }

  private void extractSecondBlock(Wc1CampData result, byte[] buffer) {
    //90 bytes per series

    //10 bytes series data
    Wc1SeriesSlot series = new Wc1SeriesSlot();
    series.setWingman(buffer[0]);
    series.setUnknown(buffer[1]);
    series.setNrOfMissions(buffer[2]);
    series.setVictoryPoints(readerHelper.getShort(new byte[] { buffer[3], buffer[4] }));
    series.setMissionTreeLevel(buffer[5]);
    series.setVictoryDestination(buffer[6]);
    series.setVictoryShip(buffer[7]);
    series.setLossDestination(buffer[8]);
    series.setLossShip(buffer[9]);

    //mission-data, 20 bytes per mission-slot

  }

  private void extractThirdBlock(Wc1CampData result, byte[] buffer) {
    //13 blocks, 8 bytes each
    List<Wc1MissionSlot> missionSlots = new ArrayList<>();
    int bufferIndex = 0;
    while (bufferIndex < buffer.length) {
      missionSlots.add(extractMissionSlot(buffer, bufferIndex));
      bufferIndex += 2;
    }
    result.setMissionSlots(missionSlots);
  }

  private Wc1MissionSlot extractMissionSlot(byte[] buffer, int firstOffset) {
    Wc1MissionSlot missionSlot = new Wc1MissionSlot();
    missionSlot.setLeftSeat(buffer[firstOffset]);
    missionSlot.setRightSeat(buffer[firstOffset + 1]);
    return missionSlot;
  }

}
