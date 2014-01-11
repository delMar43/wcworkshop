package wellfield.jsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.TreeNode;

import wellfield.jsf.factory.ProjectTreeFactory;

@RequestScoped
@SuppressWarnings("serial")
@ManagedBean(name = "projectTreeController")
public class ProjectTreeController implements Serializable {
  private ProjectTreeFactory projectTreeFactory = ProjectTreeFactory.getInstance();

  public TreeNode getProjects() {
    return projectTreeFactory.generateProjectTree("delMar");
  }
}
