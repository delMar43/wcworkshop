<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="scrollablePane">
  <h2>
    <a href="javascript:openSeriesEditor(${seriesIndex})">Series ${seriesIndex +1}</a>, 
    <a href="javascript:openMissionEditor(${seriesIndex}, ${missionIndex})">Mission ${missionIndex +1}</a>,
    NavPoint ${navPointIndex +1}
  </h2>
  
  <table>
    <tr>
      <th>Name</th>
      <td>${navPoint.id}</td>
    </tr>
    <tr>
      <th>Visible</th>
      <td>${navPoint.visible}</td>
    </tr>
    <tr>
      <th>Coordinates</th>
      <td>${navPoint.xPos} / ${navPoint.yPos}</td>
    </tr>
    <tr>
      <th>Ships Present</th>
      <td>
        <c:forEach items="${navPoint.shipsToLoad}" var="ship">
          ${shipRepo.getShip(ship).name}<br/>
        </c:forEach>
      </td>
    </tr>
    <tr>
      <th>Nav Point Manipulations</th>
      <td>
        <c:forEach items="${navPoint.navPointManipulations}" var="manip">
          ${manip.enableNavPoint ? 'show' : 'hide'}&nbsp;<a href="javascript:openNavPointEditor(${seriesIndex}, ${missionIndex}, ${manip.navPoint})">nav&nbsp;${manip.navPoint}</a><br/>
        </c:forEach>
      </td>
    </tr>
  </table>
</div>