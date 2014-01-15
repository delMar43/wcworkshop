package wcworkshop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Main implements EntryPoint {

  @Override
  public void onModuleLoad() {
    DockLayoutPanel mainPanel = createMainPanel();
    RootLayoutPanel.get().add(mainPanel);
  }

  private DockLayoutPanel createMainPanel() {
    DockLayoutPanel result = new DockLayoutPanel(Unit.EM);
    result.addNorth(new HTML("north"), 2);
    result.addWest(new HTML("nav"), 2);
    result.add(new HTML("content"));

    return result;
  }

}
