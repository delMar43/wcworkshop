<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="scrollablePane">
  <form:form id="cutsceneEditForm_${cutsceneId}" action="saveCutscene.html" method="POST">
  <form:hidden path="projectId" />
  <form:hidden path="missionId" />
  <form:hidden path="cutsceneType" />
  <div>
    <h4>Notes</h4>
    <ul>
      <li>
        Not all back- and foregrounds seem to be loaded for every cutscene type.<br/>
        ie briefing backgrounds will not work in Shotglass'
      </li>
      <li>
        The text length is limited. If too many characters are to be displayed on a page, the game will crash.<br/>
        If you find out, how many are allowed exactly, please let us know.
      </li>
      <li>
        If you find out, what the unknown values are intended for, please let us know.
      </li>
      <li>
        To change the order, please update the values in the according fields.<br/>
        If you enter invalid or ambiguous values, the result is unknown.
      </li>
      <li>
        To remove a line, click the 'del' link.
      </li>
      <li>
        Invalid facial expressions will freeze the game.
      </li>
    </ul>
    <table id="cutsceneEditTable_${cutsceneId}">
      <tbody>
        <%-- tr>
          <td title="Insert new line at this position">i</td><td colspan="4"><hr/></td>
        </tr --%>
  
    <c:forEach items="${command.screens}" var="screen" varStatus="screenStatus">
      <c:set var="screenIndex" value="${screenStatus.index}" scope="request" />
        <%@ include file="cutsceneEditorLine.jsp" %>
    </c:forEach>
        <tr id="cutsceneEditTableLastLine_${cutsceneId}">
          <td colspan="5"><a href="javascript:addScreen(nextScreenIndex)">Add Screen</a></td>
        </tr>
      </tbody>
    </table>
  </div>
  </form:form>
  <button onclick="submitCutsceneEditForm('#cutsceneEditForm_${cutsceneId}')">Save</button>
</div>
<script type="text/javascript">
var nextScreenIndex = ${screenIndex+1};
var addScreen = function(nextScreen) {
  var ajaxUrl = 'addScreen.html';
  var ajaxData = 'projectId=${command.projectId}&missionId=${command.missionId}&cutsceneType=${command.cutsceneType}&nextScreen=' + nextScreen;
  $.ajax({
    type: "GET",
    url: ajaxUrl,
    data: ajaxData,
    success: function(response) {
      $("#cutsceneEditTableLastLine_${cutsceneId}").before(response);
      ++nextScreenIndex;
      //alert(nextScreen);
    }
  });
}
</script>