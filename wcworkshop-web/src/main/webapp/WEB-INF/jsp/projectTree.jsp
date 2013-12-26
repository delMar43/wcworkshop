<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="projectTree" id="projectTree">
  <c:forEach items="${projectNodes}" var="projectNode" varStatus="projectStatus">
    <li class="project=${projectNode.id}">${projectNode.label}
      <ul>
        <c:forEach items="${projectNode.seriesNodes}" var="seriesNode" varStatus="seriesNodeStatus">
          <li class="series=${seriesNode.id},project=${projectNode.id},label=${seriesNode.label}">${seriesNode.label}
            <ul>
            <c:forEach items="${seriesNode.missionNodes}" var="missionNode" varStatus="missionNodeStatus">
              <li class="mission=${missionNode.id},project=${projectNode.id},label=${missionNode.label}">${missionNode.label}
                <ul>
                  <li>Cutscenes
                    <ul>
                      <li class="cutscene=briefing,mission=${missionNode.id},project=${projectNode.id},label=${seriesNode.label}/${missionNode.label}/Briefing">Briefing</li>
                      <li class="cutscene=debriefing,mission=${missionNode.id},project=${projectNode.id},label=${seriesNode.label}/${missionNode.label}/Debriefing">Debriefing</li>
                      <li class="cutscene=shotglass,mission=${missionNode.id},project=${projectNode.id},label=${seriesNode.label}/${missionNode.label}/Shotglass">Shotglass</li>
                      <li class="cutscene=left_seat,mission=${missionNode.id},project=${projectNode.id},label=${seriesNode.label}/${missionNode.label}/Left">Left</li>
                      <li class="cutscene=right_seat,mission=${missionNode.id},project=${projectNode.id},label=${seriesNode.label}/${missionNode.label}/Right">Right</li>
                    </ul>
                  </li>
                  <li>Nav Points
                    <ul>
                      <c:forEach items="${missionNode.navPointNodes}" var="navPointNode" varStatus="navPointStatus">
                        <li class="navPoint=${navPointNode.id} project=${projectNode.id}">${navPointNode.label}</li>
                      </c:forEach>
                    </ul>
                  </li>
                </ul>
              </li>
            </c:forEach>
            </ul>
          </li>
        </c:forEach>
      </ul>
    </li>
  </c:forEach>
</ul>
