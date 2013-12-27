package wcworkshop.web.controller.project;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.shiro.SecurityUtils;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.dto.Project;
import wcworkshop.core.model.FileModel;
import wcworkshop.core.model.ProjectDownloadModel;
import wcworkshop.core.service.FileService;
import wcworkshop.core.service.ProjectService;

@Controller
public class DownloadProjectFormController {

  private ProjectService projectService = ProjectService.getInstance();
  private FileService fileService = FileService.getInstance();
  private static final DateFormatter formatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");

  @RequestMapping("/downloads")
  public ModelAndView showDownloadLinks() {
    ModelAndView result = new ModelAndView("project/download");

    String username = (String) SecurityUtils.getSubject().getPrincipal();
    List<Project> projects = projectService.listProjects(username);
    List<ProjectDownloadModel> downloadModels = new ArrayList<>();
    for (Project project : projects) {
      ProjectDownloadModel model = new ProjectDownloadModel();
      model.setProjectId(project.getId());
      model.setProjectTitle(project.getTitle());

      List<FileModel> fileModels = new ArrayList<>();
      File[] userFiles = fileService.listUserFiles(username, project.getId());
      for (File file : userFiles) {
        fileModels.add(new FileModel(file.getName(), formatter.print(new Date(file.lastModified()), Locale.US)));
      }
      model.setFiles(fileModels);

      downloadModels.add(model);
    }

    result.addObject("downloadModels", downloadModels);

    return result;
  }
}
