package wcworkshop.web.command;

import wcworkshop.core.dto.Wc1Series;

public class SeriesCommand {

  private String projectId;
  private Wc1Series series;

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public Wc1Series getSeries() {
    return series;
  }

  public void setSeries(Wc1Series series) {
    this.series = series;
  }
}
