<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h2>Campaign ${campaign}, Series ${seriesIndex +1}</h2>
<form:form action="saveSeries.html" method="POST">
  <table>
    <tbody>
      <tr>
        <th>System</th>
        <td><form:input path="systemName" /></td>
      </tr>
      <tr>
        <th>Wingman</th>
        <td><form:input path="wingman" /></td>
      </tr>
      <tr>
        <th>Victory Points</th>
        <td><form:input path="victoryPoints" /></td>
      </tr>
      <tr>
        <th>Missiontree Level</th>
        <td><form:input path="missionTreeLevel" /></td>
      </tr>
      <tr>
        <th>Victory Destination</th>
        <td><form:input path="victoryDestination" /></td>
      </tr>
      <tr>
        <th>Victory Ship</th>
        <td><form:input path="victoryShip" /></td>
      </tr>
      <tr>
        <th>Loss Destination</th>
        <td><form:input path="lossDestination" /></td>
      </tr>
      <tr>
        <th>Loss Ship</th>
        <td><form:input path="lossShip" /></td>
      </tr>
    </tbody>
  </table>
</form:form>