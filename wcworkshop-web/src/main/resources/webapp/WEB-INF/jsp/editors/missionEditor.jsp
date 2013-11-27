<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<h2>Series ${seriesIndex +1}, Mission ${missionIndex +1}</h2>
<div>
  <h3>Mission Parameters</h3>
  <table>
    <tr>
      <th>Conversation Partners</th>
      <td>Left: ${campUtil.getPilot(mission.conversationPartners.leftSeat)}, Right: ${campUtil.getPilot(mission.conversationPartners.rightSeat)}</td>
    </tr>
    <tr>
      <th>Medal</th>
      <td>${campUtil.getMedal(mission.medal)}</td>
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
  <h3>Cutscenes</h3>
  <table>
    <tr>
      <th>Shotglass</th>
      <td><a href="javascript:openCutsceneEditor(${seriesIndex}, ${missionIndex}, 2)">Edit...</a></td>
    </tr>
    <tr>
      <th>Left Chair</th>
      <td>${campUtil.getPilot(mission.conversationPartners.leftSeat)}&nbsp;<a href="javascript:openCutsceneEditor(${seriesIndex}, ${missionIndex}, 3)">Edit...</a></td>
    </tr>
    <tr>
      <th>Right Chair</th>
      <td>${campUtil.getPilot(mission.conversationPartners.rightSeat)}&nbsp;<a href="javascript:openCutsceneEditor(${seriesIndex}, ${missionIndex}, 4)">Edit...</a></td>
    </tr>
    <tr>
      <th>Briefing</th>
      <td><a href="javascript:openCutsceneEditor(${seriesIndex}, ${missionIndex}, 0)">Edit...</a></td>
    </tr>
    <tr>
      <th>Debriefing</th>
      <td><a href="javascript:openCutsceneEditor(${seriesIndex}, ${missionIndex}, 1)">Edit...</a></td>
    </tr>
  </table>
</div>