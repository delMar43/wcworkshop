package wcworkshop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import distilled.ui.client.AppPanel;

public class Main implements EntryPoint {

  @Override
  public void onModuleLoad() {
    RootLayoutPanel.get().add(new AppPanel());
  }

}
