<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

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
                    <c:forEach items="${mission.navPoints}" var="navPoint">
                      <li>[N] ${navPoint.id}</li>
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