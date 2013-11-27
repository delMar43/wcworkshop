<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<h3>${gameData.campaignName}</h3>
<ul class="tree" id="campaignTree">
  <c:forEach items="${gameData.seriesSlots}" var="series" varStatus="seriesStatus">
    <li><a href="javascript:openSeriesEditor(${seriesStatus.index})">Series ${seriesStatus.count}</a>
      <ul>
        <c:forEach items="${series.missionSlots}" var="mission" varStatus="missionStatus">
          <li><a href="javascript:openMissionEditor(${seriesStatus.index}, ${missionStatus.index})">Mission ${missionStatus.count}</a>
            <ul>
              <!-- li>Nav Points
                <ul>
                  <li>Tiger's Claw</li>
                  <li>Nav 1</li>
                  <li>Nav 2</li>
                  <li>Nav 3</li>
                </ul>
              </li -->
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