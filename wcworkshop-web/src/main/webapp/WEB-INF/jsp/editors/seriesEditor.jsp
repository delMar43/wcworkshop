<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript">
var submitSeriesEditForm = function() {
  $.ajax({
    type: "POST",
    url: $("#seriesEditForm").attr("action"),
    data: $("#seriesEditForm").serialize(),
    success: function(response) {
      var respObj = $.parseJSON(response);
      alert(response);
    }
  });
}
</script>
<h2>Campaign ${campaign}, Series ${seriesIndex +1}</h2>
<form:form action="saveSeries.html" method="POST" id="seriesEditForm">
  <form:hidden path="projectId" />
  <form:hidden path="series.id" />
  <form:hidden path="series.seriesNr" />
  <table>
    <tbody>
      <tr>
        <th>System</th>
        <td><form:input path="series.systemName" readonly="true" disabled="true" /></td>
      </tr>
      <tr>
        <th>Wingman</th>
        <td><form:input path="series.wingman" /></td>
      </tr>
      <tr>
        <th>Victory Points</th>
        <td><form:input path="series.victoryPoints" /></td>
      </tr>
      <tr>
        <th>Missiontree Level</th>
        <td><form:input path="series.missionTreeLevel" /></td>
      </tr>
      <tr>
        <th>Victory Destination</th>
        <td><form:input path="series.victoryDestination" /></td>
      </tr>
      <tr>
        <th>Victory Ship</th>
        <td><form:input path="series.victoryShip" /></td>
      </tr>
      <tr>
        <th>Loss Destination</th>
        <td><form:input path="series.lossDestination" /></td>
      </tr>
      <tr>
        <th>Loss Ship</th>
        <td><form:input path="series.lossShip" /></td>
      </tr>
    </tbody>
  </table>
</form:form>
<button onclick="submitSeriesEditForm()">Save</button>
