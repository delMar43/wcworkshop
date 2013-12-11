<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="scrollablePane">
  <h3>${gameData.campaignName}</h3>
  <ul class="campaignTree" id="campaignTree_${campaign}">
    <c:forEach items="${gameData.seriesSlots}" var="series" varStatus="seriesStatus">
      <li><a href="javascript:openSeriesEditor('${campaign}', ${seriesStatus.index})">Series ${seriesStatus.count} (${series.systemName})</a>
        <ul>
          <c:forEach items="${series.missionSlots}" var="mission" varStatus="missionStatus">
            <li><a href="javascript:openMissionEditor('${campaign}', ${seriesStatus.index}, ${missionStatus.index})">Mission ${missionStatus.count} (${mission.wingName})</a>
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
            </li>
          </c:forEach>
        </ul>
      </li>
    </c:forEach>
  </ul>
</div>