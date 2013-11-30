<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="scrollablePane">
  <h2>
    Campaign ${campaign}, 
    <a href="javascript:openSeriesEditor('${campaign}', ${seriesIndex})">Series ${seriesIndex +1}</a>, 
    <a href="javascript:openMissionEditor('${campaign}', ${seriesIndex}, ${missionIndex})">Mission ${missionIndex +1}</a>,
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
      <th>Coordinates (x/y/z)</th>
      <td>${navPoint.xPos} / ${navPoint.yPos} / ${navPoint.zPos}</td>
    </tr>
    <tr>
      <th>Unknown 1</th>
      <td>${navPoint.unknown1}</td>
    </tr>
    <tr>
      <th>Unknown 2</th>
      <td>${navPoint.unknown2}</td>
    </tr>
    <tr>
      <th>Unknown 3</th>
      <td>${navPoint.unknown3}</td>
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
          ${manip.enableNavPoint ? 'show' : 'hide'}&nbsp;<a href="javascript:openNavPointEditor('${campaign}', ${seriesIndex}, ${missionIndex}, ${manip.navPoint})">nav&nbsp;${manip.navPoint}</a><br/>
        </c:forEach>
      </td>
    </tr>
    
    <tr>
      <th>Icon</th>
      <td>${navPointInfo.icon}</td>
    </tr>
    <tr>
      <th>Ship or NavPoint Nr</th>
      <td>${navPointInfo.pointOrShipNr}</td>
    </tr>
    <tr>
      <th>Notes</th>
      <td>${navPointInfo.text}</td>
    </tr>
  </table>
</div>