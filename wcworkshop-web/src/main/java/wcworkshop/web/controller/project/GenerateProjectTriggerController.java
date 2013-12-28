package wcworkshop.web.controller.project;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import wcworkshop.core.binary.Wc1BriefingFile;
import wcworkshop.core.binary.Wc1CampFile;
import wcworkshop.core.binary.Wc1GameFiles;
import wcworkshop.core.config.Configuration;
import wcworkshop.core.dto.Project;
import wcworkshop.core.reader.Wc1BriefingReader;
import wcworkshop.core.reader.Wc1CampReader;
import wcworkshop.core.service.ProjectService;
import wcworkshop.core.transformer.CampaignTransformer;
import wcworkshop.core.writer.Wc1BriefingWriter;
import wcworkshop.core.writer.Wc1CampWriter;

@Controller
public class GenerateProjectTriggerController {
  private static final Logger logger = LoggerFactory.getLogger(GenerateProjectTriggerController.class);

  private ProjectService projectService = ProjectService.getInstance();
  private CampaignTransformer campaignTransformer = CampaignTransformer.getInstance();
  private Wc1CampReader campReader = Wc1CampReader.getInstance();
  private Wc1BriefingReader briefingReader = Wc1BriefingReader.getInstance();
  private Wc1CampWriter campWriter = Wc1CampWriter.getInstance();
  private Wc1BriefingWriter briefingWriter = Wc1BriefingWriter.getInstance();

  @RequestMapping("/generateProject")
  public ModelAndView render(@RequestParam String projectId) {
    String username = (String) SecurityUtils.getSubject().getPrincipal();

    ModelAndView result = new ModelAndView("forward:/downloads.html");

    Project project = projectService.loadProject(username, projectId);
    Wc1GameFiles gameFiles = campaignTransformer.campaignToBinary(project.getCampaign());
    fixMissingGameFileData(gameFiles);

    String path = Configuration.getInstance().getGeneratedPath(username, projectId);

    campWriter.write(path + "CAMP.000", gameFiles.getCampFile());
    briefingWriter.write(path + "BRIEFING.000", gameFiles.getBriefingFile());

    return result;
  }

  private void fixMissingGameFileData(Wc1GameFiles gameFiles) {
    Wc1CampFile wc1CampFile = campReader.read("000");
    Wc1CampFile newCampFile = gameFiles.getCampFile();

    newCampFile.setBarData(wc1CampFile.getBarData());
    newCampFile.setStellarBackgrounds(wc1CampFile.getStellarBackgrounds());

    Wc1BriefingFile wc1BriefingFile = briefingReader.read("000");
    Wc1BriefingFile newBriefingFile = gameFiles.getBriefingFile();

    newBriefingFile.setFuneralData(wc1BriefingFile.getFuneralData());
    newBriefingFile.setHalcyon(wc1BriefingFile.getHalcyon());
    newBriefingFile.setMedalCeremony(wc1BriefingFile.getMedalCeremony());
  }
}
