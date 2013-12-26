package wcworkshop.core.dto;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import wcworkshop.core.service.Wc1CutsceneType;

public class Wc1Mission {

  private String id;
  private String wingName;
  private short[] stellarBackgrounds;
  private byte leftPilot;
  private byte rightPilot;
  private short medal;
  private short requiredMedalPoints;
  private short[] victoryPointsPerObjective; //TODO save victorypoint with the objective
  private short[] autopilotShips;

  private Map<Wc1CutsceneType, Wc1Cutscene> cutscenes;
  private List<Wc1NavPoint> navPoints;
  private List<Wc1ShipAssignment> shipAssignments;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWingName() {
    return wingName;
  }

  public void setWingName(String wingName) {
    this.wingName = StringUtils.trim(wingName);
  }

  public short[] getStellarBackgrounds() {
    return stellarBackgrounds;
  }

  public void setStellarBackgrounds(short[] stellarBackgrounds) {
    this.stellarBackgrounds = stellarBackgrounds;
  }

  public byte getLeftPilot() {
    return leftPilot;
  }

  public void setLeftPilot(byte leftPilot) {
    this.leftPilot = leftPilot;
  }

  public byte getRightPilot() {
    return rightPilot;
  }

  public void setRightPilot(byte rightPilot) {
    this.rightPilot = rightPilot;
  }

  public short getMedal() {
    return medal;
  }

  public void setMedal(short medal) {
    this.medal = medal;
  }

  public short getRequiredMedalPoints() {
    return requiredMedalPoints;
  }

  public void setRequiredMedalPoints(short requiredMedalPoints) {
    this.requiredMedalPoints = requiredMedalPoints;
  }

  public short[] getVictoryPointsPerObjective() {
    return victoryPointsPerObjective;
  }

  public void setVictoryPointsPerObjective(short[] victoryPointsPerObjective) {
    this.victoryPointsPerObjective = victoryPointsPerObjective;
  }

  public Map<Wc1CutsceneType, Wc1Cutscene> getCutscenes() {
    return cutscenes;
  }

  public void setCutscenes(Map<Wc1CutsceneType, Wc1Cutscene> cutscenes) {
    this.cutscenes = cutscenes;
  }

  public List<Wc1NavPoint> getNavPoints() {
    return navPoints;
  }

  public void setNavPoints(List<Wc1NavPoint> navPoints) {
    this.navPoints = navPoints;
  }

  public void setAutopilotShips(short[] autopilotShips) {
    this.autopilotShips = autopilotShips;
  }

  public short[] getAutopilotShips() {
    return autopilotShips;
  }

  public List<Wc1ShipAssignment> getShipAssignments() {
    return shipAssignments;
  }

  public void setShipAssignments(List<Wc1ShipAssignment> shipAssignments) {
    this.shipAssignments = shipAssignments;
  }
}
