package distilled.ui.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class AppPanel extends Composite {

  //@formatter:off
  private static AppPanelUiBinder uiBinder = GWT.create(AppPanelUiBinder.class);
  interface AppPanelUiBinder extends UiBinder<Widget, AppPanel> {}
  //@formatter:on
  
  private Map<String, TreeItem> treeItems = new HashMap<>();
  
  
  @UiField MenuBar menuBar;
//  @UiField MenuBar toolBar;
  @UiField Tree navTree;
  @UiField TabLayoutPanel contentTabPanel;

  public AppPanel() {
    initWidget(uiBinder.createAndBindUi(this));
    
    setup();
  }

  private void setup() {
  }

  public void addProject(String projectName) {
    TreeItem newItem = new TreeItem();
    newItem.setText(projectName);
    treeItems.put(projectName, newItem);
    navTree.addItem(newItem);
  }

  public void addFile (String projectName, String fileName) {
    TreeItem item = new TreeItem();
    item.setText(fileName);
    treeItems.get(projectName).addItem(item);
    
    contentTabPanel.add(new HTML(fileName), fileName);
  }

}
