<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>${gameData.campaignName}</h3>
<ul id="campaignTree">
  <c:forEach items="${gameData.seriesSlots}" var="series" varStatus="seriesStatus">
    <li>Series ${seriesStatus.count}
      <ul>
        <c:forEach items="${series.missionSlots}" var="mission" varStatus="missionStatus">
          <li>Mission ${missionStatus.count}
            <ul>
              <li>Nav Points
                <ul>
                  <li>Tiger's Claw</li>
                  <li>Nav 1</li>
                  <li>Nav 2</li>
                  <li>Nav 3</li>
                </ul>
              </li>
            </ul>
          </li>
          <ul>
            <li>Cutscenes
              <ul>
                <li>Shotglass</li>
                <li>Left Chair</li>
                <li>Right Chair</li>
                <li>Briefing</li>
                <li>Debriefing</li>
              </ul>
            </li>
          </ul>
        </c:forEach>
      </ul>
    </li>
  </c:forEach>
</ul>