package wellfield.jsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.TreeNode;

import wcworkshop.core.model.tree.AbstractNode;
import wcworkshop.core.model.tree.CutsceneNodeData;
import wcworkshop.core.model.tree.MissionNodeData;
import wcworkshop.core.model.tree.NavPointNodeData;
import wcworkshop.core.model.tree.ProjectNodeData;
import wcworkshop.core.model.tree.SeriesNodeData;
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
    AbstractNode data = (AbstractNode) event.getTreeNode().getData();

    int idx = -1;
    for (UIComponent childTab : editorTabs.getChildren()) {
      String childKey = (String) childTab.getAttributes().get("key");
      ++idx;
      if (childKey.equals(data.getKey())) {
        if (idx == editorTabs.getActiveIndex()) {
          return;
        }
        editorTabs.setActiveIndex(idx);
        RequestContext.getCurrentInstance().update(editorTabs.getClientId());
        return;
      }
    }

    String title;
    if (data instanceof ProjectNodeData) {
      ProjectNodeData nodeData = (ProjectNodeData) data;
      title = nodeData.getTabLabel();
    } else if (data instanceof SeriesNodeData) {
      SeriesNodeData nodeData = (SeriesNodeData) data;
      title = nodeData.getTabLabel();
    } else if (data instanceof MissionNodeData) {
      MissionNodeData nodeData = (MissionNodeData) data;
      title = nodeData.getTabLabel();
    } else if (data instanceof CutsceneNodeData) {
      CutsceneNodeData nodeData = (CutsceneNodeData) data;
      title = nodeData.getTabLabel();
    } else if (data instanceof NavPointNodeData) {
      NavPointNodeData nodeData = (NavPointNodeData) data;
      title = nodeData.getTabLabel();
    } else {
      //do nothing
      return;
    }

    tab.setTitle(title);
    tab.setClosable(true);
    tab.getAttributes().put("key", data.getKey());
    editorTabs.getChildren().add(tab);
    editorTabs.setActiveIndex(editorTabs.getChildCount() - 1);

    RequestContext.getCurrentInstance().update(editorTabs.getClientId());
  }

  public void onEditorTabClose(TabCloseEvent event) {
    editorTabs.getChildren().remove(event.getTab());
  }

  public TabView getEditorTabs() {
    editorTabs = (TabView) FacesContext.getCurrentInstance().getApplication().createComponent("org.primefaces.component.TabView");
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
