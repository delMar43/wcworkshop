package distilled.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

public class AppPanel extends Composite {

  //@formatter:off
  private static AppPanelUiBinder uiBinder = GWT.create(AppPanelUiBinder.class);
  interface AppPanelUiBinder extends UiBinder<Widget, AppPanel> {}
  //@formatter:on
  
  @UiField MenuBar menuBar;
  @UiField Tree navTree;
  @UiField TabLayoutPanel contentTabPanel;

  public AppPanel() {
    initWidget(uiBinder.createAndBindUi(this));
    
    setup();
  }

  private void setup() {
  }


}
