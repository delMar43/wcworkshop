<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
              <!-- li>Cutscenes
                <ul>
                  <li>Shotglass</li>
                  <li>Left Chair</li>
                  <li>Right Chair</li>
                  <li>Briefing</li>
                  <li>Debriefing</li>
                </ul>
              </li -->
            </ul>
          </li>
        </c:forEach>
      </ul>
    </li>
  </c:forEach>
</ul>