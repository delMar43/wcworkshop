package wcworkshop.core.data;

import java.util.List;

public class Wc1BriefingData {
  public static final Wc1BriefingData EMPTY = new Wc1BriefingData();

  private Wc1FuneralData funeralData;
  private List<Wc1MissionSlot> missionSlots;

  public List<Wc1MissionSlot> getMissionSlots() {
    return missionSlots;
  }

  public void setMissionSlots(List<Wc1MissionSlot> missionSlots) {
    this.missionSlots = missionSlots;
  }
}
