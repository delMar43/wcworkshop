package wcworkshop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import distilled.ui.client.AppPanel;

public class Main implements EntryPoint {

  private AppPanel rootPanel = new AppPanel();
  
  @Override
  public void onModuleLoad() {
    RootLayoutPanel.get().add(rootPanel);
    
    rootPanel.addProject("Wing Commander");
    rootPanel.addFile("Wing Commander", "File 1");
    
  }

}
