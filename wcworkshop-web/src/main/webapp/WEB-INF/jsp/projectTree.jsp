<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="projectTree" id="projectTree">
  <c:forEach items="${projectNodes}" var="projectNode" varStatus="projectStatus">
    <li class="project ${projectNode.id}">${projectNode.label}
      <ul>
        <c:forEach items="${projectNode.seriesNodes}" var="seriesNode" varStatus="seriesNodeStatus">
          <li class="series ${seriesNode.id}">${seriesNode.label}
          <ul>
          <c:forEach items="${seriesNode.missionNodes}" var="missionNode" varStatus="missionNodeStatus">
            <li>${missionNode.label}
              <ul>
                <li>Cutscenes
                  <ul>
                    <li>Briefing</li>
                    <li>Debriefing</li>
                    <li>Shotglass</li>
                    <li>Left</li>
                    <li>Right</li>
                  </ul>
                </li>
                <li>Nav Points
                  <ul>
                    <c:forEach items="${missionNode.navPointNodes}" var="navPointNode" varStatus="navPointStatus">
                      <li>${navPointNode.label}</li>
                    </c:forEach>
                  </ul>
                </li>
              </ul>
            </li>
          </c:forEach>
          </ul>
          <%--
            <ul>
              <li>Nav Points
                <ul>
                  <c:forEach items="${mission.navPoints}" var="navPoint" varStatus="navPointStatus">
                    <li><a href="javascript:openNavPointEditor('${campaign}', ${seriesStatus.index}, ${missionStatus.index}, ${navPointStatus.index})">
                      [N] ${navPointStatus.index}: ${navPoint.id}
                    </a></li>
                  </c:forEach>
                </ul>
              </li>
              <li><a href="javascript:openCutsceneEditor('${campaign}', ${seriesStatus.index}, ${missionStatus.index}, 'SHOTGLASS')">[C] Shotglass</a></li>
              <li><a href="javascript:openCutsceneEditor('${campaign}', ${seriesStatus.index}, ${missionStatus.index}, 'LEFT_SEAT')">[C] Left Seat</a></li>
              <li><a href="javascript:openCutsceneEditor('${campaign}', ${seriesStatus.index}, ${missionStatus.index}, 'RIGHT_SEAT')">[C] Right Seat</a></li>
              <li><a href="javascript:openCutsceneEditor('${campaign}', ${seriesStatus.index}, ${missionStatus.index}, 'BRIEFING')">[C] Briefing</a></li>
              <li><a href="javascript:openCutsceneEditor('${campaign}', ${seriesStatus.index}, ${missionStatus.index}, 'DEBRIEFING')">[C] Debriefing</a></li>
            </ul>
             --%>
          </li>
        </c:forEach>
      </ul>
    </li>
  </c:forEach>
</ul>
