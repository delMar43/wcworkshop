package wcworkshop.web.controller.project;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wcworkshop.core.config.Configuration;

@Controller
public class DownloadLinkController {
  private static final Logger logger = LoggerFactory.getLogger(DownloadLinkController.class);

  @ResponseBody
  @RequestMapping(value = "/generated/{projectId}/{filename}.bin", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public byte[] deliverFile(@PathVariable String projectId, @PathVariable String filename, HttpServletResponse response) {
    String username = (String) SecurityUtils.getSubject().getPrincipal();

    String fullPath = Configuration.getInstance().getGeneratedPath(username, projectId) + filename;
    File file = new File(fullPath);

    byte[] data = readFileToByteArray(fullPath, file);

    response.setHeader("Content-disposition", "attachment; filename=" + filename.replace(".bin", ""));

    return data;
  }

  private byte[] readFileToByteArray(String fullPath, File file) {
    byte[] data;
    try {
      data = FileUtils.readFileToByteArray(file);
      return data;
    } catch (IOException e) {
      logger.error("Exception while trying to read file " + fullPath + ": " + e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
