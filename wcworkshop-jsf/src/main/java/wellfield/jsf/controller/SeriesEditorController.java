package wellfield.jsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import wcworkshop.core.dto.Wc1Series;
import wcworkshop.core.service.ProjectService;
import wellfield.jsf.model.SeriesEditModel;

@SessionScoped
@SuppressWarnings("serial")
@ManagedBean(name = "seriesEditorController")
public class SeriesEditorController implements Serializable {
  private ProjectService projectService = ProjectService.getInstance();

  public SeriesEditModel getSeriesEditModel() {
    Wc1Series series = projectService.loadSeries("delMar", "projectId", "seriesId");

    return new SeriesEditModel();
  }
}
