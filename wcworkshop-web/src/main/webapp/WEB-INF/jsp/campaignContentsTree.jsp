<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="scrollablePane">
  <h3>${gameData.campaignName}</h3>
  <ul class="tree" id="campaignTree">
    <c:forEach items="${gameData.seriesSlots}" var="series" varStatus="seriesStatus">
      <li><a href="javascript:openSeriesEditor(${seriesStatus.index})">Series ${seriesStatus.count} (${series.systemName})</a>
        <ul>
          <c:forEach items="${series.missionSlots}" var="mission" varStatus="missionStatus">
            <li><a href="javascript:openMissionEditor(${seriesStatus.index}, ${missionStatus.index})">Mission ${missionStatus.count} (${mission.wingName})</a>
              <ul>
                <li>Nav Points
                  <ul>
                    <c:forEach items="${mission.navPoints}" var="navPoint" varStatus="navPointStatus">
                      <li><a href="javascript:openNavPointEditor(${seriesStatus.index}, ${missionStatus.index}, ${navPointStatus.index})">
                        ${navPointStatus.index}: [N] ${navPoint.id}
                      </a></li>
                    </c:forEach>
                  </ul>
                </li>
                <li><a href="javascript:openCutsceneEditor(${seriesStatus.index}, ${missionStatus.index}, 2)">[C] Shotglass</a></li>
                <li><a href="javascript:openCutsceneEditor(${seriesStatus.index}, ${missionStatus.index}, 3)">[C] Left Seat</a></li>
                <li><a href="javascript:openCutsceneEditor(${seriesStatus.index}, ${missionStatus.index}, 4)">[C] Right Seat</a></li>
                <li><a href="javascript:openCutsceneEditor(${seriesStatus.index}, ${missionStatus.index}, 0)">[C] Briefing</a></li>
                <li><a href="javascript:openCutsceneEditor(${seriesStatus.index}, ${missionStatus.index}, 1)">[C] Debriefing</a></li>
              </ul>
            </li>
          </c:forEach>
        </ul>
      </li>
    </c:forEach>
  </ul>
</div>