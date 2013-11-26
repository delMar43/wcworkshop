<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
  <tbody>
    <tr>
      <th>Wingman</th>
      <td>${campUtil.getPilot(series.wingman)}</td>
    </tr>
    <tr>
      <th>Nr of Missions</th>
      <td>${series.nrOfMissions}</td>
    </tr>
    <tr>
      <th>Victory Points</th>
      <td>${series.victoryPoints}</td>
    </tr>
    <tr>
      <th>Missiontree Level</th>
      <td>${series.missionTreeLevel}</td>
    </tr>
    <tr>
      <th>Victory Destination</th>
      <td><a href="javascript:openSeriesEditor(${series.victoryDestination-1})">${series.victoryDestination}</a></td>
    </tr>
    <tr>
      <th>Victory Ship</th>
      <td>${series.victoryShip}</td>
    </tr>
    <tr>
      <th>Loss Destination</th>
      <td><a href="javascript:openSeriesEditor(${series.lossDestination-1})">${series.lossDestination}</a></td>
    </tr>
    <tr>
      <th>Loss Ship</th>
      <td>${series.lossShip}</td>
    </tr>
  </tbody>
</table>

<ol>  
  <c:forEach items="${series.missionSlots}" var="mission" varStatus="missionStatus">
    <li><a href="javascript:openMissionEditor(${seriesIndex}, ${missionStatus.index})">Mission ${missionStatus.count}</a></li>
  </c:forEach>
</ol>
