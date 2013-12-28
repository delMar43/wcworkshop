<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="scrollablePane" id="missionDiv_${command.mission.id}">
<div>
  <h3>Mission Parameters</h3>
  <form:form id="missionEditForm_${command.mission.id}" method="POST" action="saveMission.html">
    <form:hidden path="projectId" />
    <form:hidden path="mission.id" />
    <table>
      <tr>
        <th>Wing</th>
        <td><form:input path="mission.wingName" /></td>
      </tr>
      <%-- tr>
        <th>Stellar Backgrounds?</th>
        <td>
          <c:forEach items="${mission.unknown}" var="unknownPart">
            0x${unknownPart}
          </c:forEach>
        </td>
      </tr --%>
      <tr>
        <th>Left Bar Seat</th>
        <td>
          <form:select path="mission.leftPilot">
            <form:options items="${pilots}" itemLabel="name" itemValue="value"/>
          </form:select>
        </td>
      </tr>
      <tr>
        <th>Right Bar Seat</th>
        <td>
          <form:select path="mission.rightPilot">
            <form:options items="${pilots}" itemLabel="name" itemValue="value"/>
          </form:select>
        </td>
      </tr>
      <tr>
        <th>Medal</th>
        <td>
          <form:select path="mission.medal">
            <form:options items="${medals}" itemLabel="name" itemValue="value" />
          </form:select>
        </td>
      </tr>
      <tr>
        <th>Required Killpoints for Medal</th>
        <td>
          <form:input path="mission.requiredMedalPoints" />
        </td>
      </tr>
      <%-- tr>
        <th>Objective Victory Points</th>
        <td>
          <form:input path="mission.victoryPointsPerObjective" />
        </td>
      </tr --%>
    </table>
  </form:form>
  <button onclick="submitMissionEditForm('#missionEditForm_${command.mission.id}')">Save</button>
</div>
<%--
<div>
  <h3>Ships</h3>
  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Type</th>
        <th>Friend / Foe</th>
        <th>Orders</th>
        <th>Coordinates</th>
        <th>Speed</th>
        <th>Size</th>
        <th>Pilot</th>
        <th>Primary Target</th>
        <th>Secondary Target</th>
        <th>Formation</th>
        <th>Autopilot</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${mission.shipData}" var="shipData" varStatus="shipDataStatus">
      <tr>
        <td>${shipDataStatus.index}</td>
        <td>${shipRepo.getShip(shipData.type).name}</td>
        <td>${iffRepo.getIff(shipData.iff)}</td>
        <td>[${shipData.orders}] ${orderRepo.getOrder(shipData.orders).text}</td>
        <td>${shipData.xCoord} / ${shipData.yCoord} / ${shipData.zCoord}</td>
        <td>${shipData.speed}</td>
        <td>${shipData.size}</td>
        <td>${aiPilotRepo.getAiPilot(shipData.aiPilot).name}</td>
        <td>${shipData.primaryTarget}</td>
        <td>${shipData.secondaryTarget}</td>
        <td>${shipData.formation}</td>
        <td><c:if test="${shipData.autopilotShip}">*</c:if></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<div>
  <h3>Cutscenes</h3>
  <table>
    <tr>
      <th>Shotglass</th>
      <td><a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 'SHOTGLASS')">Edit...</a></td>
    </tr>
    <tr>
      <th>Left Chair</th>
      <td>${pilotRepo.getPilot(mission.conversationPartners.leftSeat).name}&nbsp;<a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 'LEFT_SEAT')">Edit...</a></td>
    </tr>
    <tr>
      <th>Right Chair</th>
      <td>${pilotRepo.getPilot(mission.conversationPartners.rightSeat).name}&nbsp;<a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 'RIGHT_SEAT')">Edit...</a></td>
    </tr>
    <tr>
      <th>Briefing</th>
      <td><a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 'BRIEFING')">Edit...</a></td>
    </tr>
    <tr>
      <th>Debriefing</th>
      <td><a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 'DEBRIEFING')">Edit...</a></td>
    </tr>
  </table>
</div>
 --%>
</div>