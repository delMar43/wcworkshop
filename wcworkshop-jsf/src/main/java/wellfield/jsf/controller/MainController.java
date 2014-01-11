package wellfield.jsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import wellfield.jsf.factory.ProjectTreeFactory;

@RequestScoped
@SuppressWarnings("serial")
@ManagedBean(name = "mainController")
public class MainController implements Serializable {
  private ProjectTreeFactory projectTreeFactory = ProjectTreeFactory.getInstance();

  private TabView editorTabs = new TabView();
  private TreeNode selectedNode;

  public TreeNode getProjects() {
    return projectTreeFactory.generateProjectTree("delMar");
  }

  public void onNodeSelect(NodeSelectEvent event) {
    Tab tab = new Tab();
    tab.setTitle("source " + event.getSource());
    editorTabs.getChildren().add(tab);

    RequestContext.getCurrentInstance().update("editorTabs");
  }

  public TabView getEditorTabs() {
    return editorTabs;
  }

  public void setEditorTabs(TabView editorTabs) {
    this.editorTabs = editorTabs;
  }

  public TreeNode getSelectedNode() {
    return selectedNode;
  }

  public void setSelectedNode(TreeNode selectedNode) {
    this.selectedNode = selectedNode;
  }
}
