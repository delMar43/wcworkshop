<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="scrollablePane">
<h2>
  Campaign ${campaign}, <a href="javascript:openSeriesEditor('${campaign}', ${seriesIndex})">Series ${seriesIndex +1}</a>, Mission ${missionIndex +1}
</h2>
<div>
  <h3>Mission Parameters</h3>
  <table>
    <tr>
      <th>Wing</th>
      <td>${mission.wingName}</td>
    </tr>
    <tr>
      <th>Stellar Backgrounds?</th>
      <td>
        <c:forEach items="${mission.unknown}" var="unknownPart">
          0x${unknownPart}
        </c:forEach>
      </td>
    </tr>
    <tr>
      <th>Conversation Partners</th>
      <td>Left: ${pilotRepo.getPilot(mission.conversationPartners.leftSeat).name}, Right: ${pilotRepo.getPilot(mission.conversationPartners.rightSeat).name}</td>
    </tr>
    <tr>
      <th>Medal</th>
      <td>${medalRepo.getMedal(mission.medal).name}</td>
    </tr>
    <tr>
      <th>Medal Killpoints</th>
      <td>${mission.medalKillPoints}</td>
    </tr>
    <tr>
      <th>Objective Victory Points</th>
      <td>${objectiveVictoryPoints}</td>
    </tr>
  </table>
</div>

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
      <td><a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 2)">Edit...</a></td>
    </tr>
    <tr>
      <th>Left Chair</th>
      <td>${pilotRepo.getPilot(mission.conversationPartners.leftSeat).name}&nbsp;<a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 3)">Edit...</a></td>
    </tr>
    <tr>
      <th>Right Chair</th>
      <td>${pilotRepo.getPilot(mission.conversationPartners.rightSeat).name}&nbsp;<a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 4)">Edit...</a></td>
    </tr>
    <tr>
      <th>Briefing</th>
      <td><a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 0)">Edit...</a></td>
    </tr>
    <tr>
      <th>Debriefing</th>
      <td><a href="javascript:openCutsceneEditor('${campaign}', ${seriesIndex}, ${missionIndex}, 1)">Edit...</a></td>
    </tr>
  </table>
</div>
</div>