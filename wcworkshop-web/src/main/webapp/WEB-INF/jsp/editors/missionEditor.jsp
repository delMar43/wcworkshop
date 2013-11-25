<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
  <tr>
    <th>Conversation Partners</th>
    <td>Left: ${pilots.get(mission.conversationPartners.leftSeat)}, Right: ${pilots.get(mission.conversationPartners.rightSeat)}</td>
  </tr>
  <tr>
    <th>Medal</th>
    <td>${mission.medal}</td>
  </tr>
  <tr>
    <th>Medal Killpoints</th>
    <td>${mission.medalKillPoints}</td>
  </tr>
</table>