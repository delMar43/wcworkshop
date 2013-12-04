<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
  $("#savegameForm").on("submit", function(event) {
    event.preventDefault();
    $.ajax({
      type: "POST",
      url: $("#savegameForm").attr("action"),
      data: $("#savegameForm").serialize(),
      success: function(data, textStatus, jqXHR) {
        $("#tab-savegameEditor").html(data);
      }
    });
  });
</script>
<div>
Hi there
</div>
<div style="margin-top:50px" class="scrollablePane">
  <div>Special Thanks to David S. Raley for creating WCSAV, which was used to identify savegame file offsets.</div>
  <form:form action="savegame.html" method="POST" id="savegameForm">
    <c:forEach items="${command.savegames}" var="savegame" varStatus="savegameStatus">
      <fieldset>
        <legend>Savegame ${savegameStatus.count}</legend>
        Name: <input name="savegames[${savegameStatus.index}].name" value="${savegame.name}"></input><br/>
        Campaign: <input name="savegames[${savegameStatus.index}].campaign" value="${savegame.campaign}"></input><br/>
        Series: <input name="savegames[${savegameStatus.index}].series" value="${savegame.series}"></input><br/>
        Mission: <input name="savegames[${savegameStatus.index}].mission" value="${savegame.mission}"></input><br/>
      </fieldset>
    </c:forEach>
    <button type="submit">Save</button>&nbsp;<button type="reset">Reset</button>
  </form:form>

</div>