<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="scrollablePane">
  <h2>
    Campaign ${projectId}, 
    <%-- a href="javascript:openSeriesEditor('${campaign}', ${seriesIndex +0})">Series ${seriesIndex +1}</a>,
    <a href="javascript:openMissionEditor('${campaign}', ${seriesIndex +0}, ${missionIndex +0})">Mission ${missionIndex +1}</a>: ${cutsceneIndex} --%>
  </h2>
  
  <form:form id="cutsceneEditForm_${cutsceneId}" action="saveCutscene.html" method="POST">
  <form:hidden path="projectId" />
  <form:hidden path="missionId" />
  <form:hidden path="cutsceneType" />
  <div>
    <h3>Screens</h3>
    <table>
      <thead>
        <tr>
          <th></th>
          <th>Nr</th>
          <th>Foreground</th>
          <th>Background</th>
          <th>Color</th>
          <th>Commands</th>
          <th>Text</th>
          <th>Facial expression</th>
          <th>Phonetic</th>
          <th>Unknown</th>
        </tr>
      </thead>
      <tbody>
  <c:forEach items="${command.screens}" var="screen" varStatus="screenStatus">
        <tr>
          <td>x</td>
          <td>${screenStatus.count}</td>
          <td>
            <select name="screens[${screenStatus.index}].foreground">
              <c:forEach items="${foregrounds}" var="foreground">
                <c:if test="${foreground.value != -2}">
                  <option label="${foreground.label}" value="${foreground.value}" <c:if test="${foreground.value == command.screens[screenStatus.index].foreground}">selected="selected"</c:if>></option>
                </c:if>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="screens[${screenStatus.index}].background">
              <c:forEach items="${backgrounds}" var="background">
                <option label="${background.label}" value="${background.value}" <c:if test="${background.value == command.screens[screenStatus.index].background}">selected="selected"</c:if>></option>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="screens[${screenStatus.index}].textColor">
              <c:forEach items="${textColors}" var="textColor">
                <option label="${textColor.label}" value="${textColor.value}" <c:if test="${textColor.value == command.screens[screenStatus.index].textColor}">selected="selected"</c:if>></option>
              </c:forEach>
            </select>
          </td>
          <td>
            <c:forEach items="${screen.commands}" var="screenCommand" varStatus="screenCommandStatus">
              cmd:${screenCommand.codeString}<br/>param:${screenCommand.parameterString}
              <input type="hidden" name="screens[${screenStatus.index}].commands[${screenCommandStatus.index}].codeString" value="${command.screens[screenStatus.index].commands[screenCommandStatus.index].codeString}">
              <input type="hidden" name="screens[${screenStatus.index}].commands[${screenCommandStatus.index}].parameterString" value="${command.screens[screenStatus.index].commands[screenCommandStatus.index].parameterString}">
            </c:forEach>
          </td>
          <td><textarea name="screens[${screenStatus.index}].text" cols="80" rows="2">${command.screens[screenStatus.index].text}</textarea></td>
          <td><input name="screens[${screenStatus.index}].facialExpression" value="${command.screens[screenStatus.index].facialExpression}"></td>
          <td><input name="screens[${screenStatus.index}].phonetic" value="${command.screens[screenStatus.index].phonetic}"></td>
          <td><input name="screens[${screenStatus.index}].unknown" value="${command.screens[screenStatus.index].unknown}"></td>
        </tr>
  </c:forEach>
      </tbody>
    </table>
  </div>
  </form:form>
  <button onclick="submitCutsceneEditForm('#cutsceneEditForm_${cutsceneId}')">Save</button>
</div>