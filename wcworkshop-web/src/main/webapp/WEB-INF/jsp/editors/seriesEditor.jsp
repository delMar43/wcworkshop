<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Campaign ${campaign}, Series ${seriesIndex +1}</h2>
<table>
  <tbody>
    <tr>
      <th>System</th>
      <td>${series.systemName}</td>
    </tr>
    <tr>
      <th>Wingman</th>
      <td>${pilotRepo.getPilot(series.wingman).name}</td>
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
      <td><a href="javascript:openSeriesEditor('${campaign}', ${series.victoryDestination-1})">Series ${series.victoryDestination}</a></td>
    </tr>
    <tr>
      <th>Victory Ship</th>
      <td>${shipRepo.getShip(series.victoryShip).name}</td>
    </tr>
    <tr>
      <th>Loss Destination</th>
      <td><a href="javascript:openSeriesEditor('${campaign}', ${series.lossDestination-1})">Series ${series.lossDestination}</a></td>
    </tr>
    <tr>
      <th>Loss Ship</th>
      <td>${shipRepo.getShip(series.lossShip).name}</td>
    </tr>
  </tbody>
</table>

<h3>Missions</h3>
<ol>  
  <c:forEach items="${series.missionSlots}" var="mission" varStatus="missionStatus">
    <li><a href="javascript:openMissionEditor('${campaign}', ${seriesIndex}, ${missionStatus.index})">Mission ${missionStatus.count}</a></li>
  </c:forEach>
</ol>
