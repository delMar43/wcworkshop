package wcworkshop.core.binary;

public class Wc1ModuleFile {

  private int filesize;
  private Wc1ModuleBlock1 block1; //autopilot
  private Wc1ModuleBlock2 block2; //navpoints
  private Wc1ModuleBlock3 block3; //navpoint notes
  private Wc1ModuleBlock4 block4; //ship mission data
  private String[] difficultyLevels; //never used
  private String[] wingNames;
  private byte[] zeroes;
  private String projectTitle;
  private String[] systemNames;
  private String[] hiddenSystems;

  public int getFilesize() {
    return filesize;
  }

  public void setFilesize(int filesize) {
    this.filesize = filesize;
  }

  public Wc1ModuleBlock1 getBlock1() {
    return block1;
  }

  public void setBlock1(Wc1ModuleBlock1 block1) {
    this.block1 = block1;
  }

  public Wc1ModuleBlock2 getBlock2() {
    return block2;
  }

  public void setBlock2(Wc1ModuleBlock2 block2) {
    this.block2 = block2;
  }

  public Wc1ModuleBlock3 getBlock3() {
    return block3;
  }

  public void setBlock3(Wc1ModuleBlock3 block3) {
    this.block3 = block3;
  }

  public Wc1ModuleBlock4 getBlock4() {
    return block4;
  }

  public void setBlock4(Wc1ModuleBlock4 block4) {
    this.block4 = block4;
  }

  public String[] getDifficultyLevels() {
    return difficultyLevels;
  }

  public void setDifficultyLevels(String[] difficultyLevels) {
    this.difficultyLevels = difficultyLevels;
  }

  public String[] getWingNames() {
    return wingNames;
  }

  public void setWingNames(String[] wingNames) {
    this.wingNames = wingNames;
  }

  public byte[] getZeroes() {
    return zeroes;
  }

  public void setZeroes(byte[] zeroes) {
    this.zeroes = zeroes;
  }

  public String getProjectTitle() {
    return projectTitle;
  }

  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }

  public String[] getSystemNames() {
    return systemNames;
  }

  public void setSystemNames(String[] systemNames) {
    this.systemNames = systemNames;
  }

  public String[] getHiddenSystems() {
    return hiddenSystems;
  }

  public void setHiddenSystems(String[] hiddenSystems) {
    this.hiddenSystems = hiddenSystems;
  }

}
