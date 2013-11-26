<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

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

