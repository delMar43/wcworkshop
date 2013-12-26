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
        The text length is limited. If too many characters are to be displayed on a page, the game will crash.
      </li>
      <li>
        If you find out, what the unknown values are intended for, please let us know.
      </li>
    </ul>
    <table id="cutsceneEditTable_${cutsceneId}">
      <tbody>
        <tr>
          <td>i</td><td colspan="4"><hr/></td>
        </tr>
  <c:forEach items="${command.screens}" var="screen" varStatus="screenStatus">
        <tr>
          <td title="Index of current line">${screenStatus.count}</td>
          <th>Commands</th>
          <td>
            <c:forEach items="${screen.commands}" var="screenCommand" varStatus="screenCommandStatus">
              cmd:${screenCommand.codeString}, param:${screenCommand.parameterString}
              <input type="hidden" name="screens[${screenStatus.index}].commands[${screenCommandStatus.index}].codeString" value="${command.screens[screenStatus.index].commands[screenCommandStatus.index].codeString}">
              <input type="hidden" name="screens[${screenStatus.index}].commands[${screenCommandStatus.index}].parameterString" value="${command.screens[screenStatus.index].commands[screenCommandStatus.index].parameterString}">
            </c:forEach>
          </td>
          <th>Text</th>
          <td rowspan="2">
            <textarea name="screens[${screenStatus.index}].text" cols="80" rows="2">${command.screens[screenStatus.index].text}</textarea>
          </td>
        </tr>
        <tr>
          <td title="Move current line up">up</td>
          <th>Foreground</th>
          <td>
            <select name="screens[${screenStatus.index}].foreground" style="width:98%">
              <c:forEach items="${foregrounds}" var="foreground">
                <c:if test="${foreground.value != -2}">
                  <option label="${foreground.label}" value="${foreground.value}" <c:if test="${foreground.value == command.screens[screenStatus.index].foreground}">selected="selected"</c:if>></option>
                </c:if>
              </c:forEach>
            </select>
          </td>
        </tr>
        <tr>
          <td title="Delete current line">del</td>
          <th>Background</th>
          <td>
            <select name="screens[${screenStatus.index}].background" style="width:98%">
              <c:forEach items="${backgrounds}" var="background">
                <option label="${background.label}" value="${background.value}" <c:if test="${background.value == command.screens[screenStatus.index].background}">selected="selected"</c:if>></option>
              </c:forEach>
            </select>
          </td>
          <th>Phonetics</th>
          <td rowspan="2">
            <textarea name="screens[${screenStatus.index}].phonetic" cols="80" rows="2">${command.screens[screenStatus.index].phonetic}</textarea>
          </td>
        </tr>
        <tr>
          <td title="Move current line down">down</td>
          <th>Text Color</th>
          <td>
            <select name="screens[${screenStatus.index}].textColor" style="width:98%">
              <c:forEach items="${textColors}" var="textColor">
                <option label="${textColor.label}" value="${textColor.value}" <c:if test="${textColor.value == command.screens[screenStatus.index].textColor}">selected="selected"</c:if>></option>
              </c:forEach>
            </select>
          </td>
        </tr>
        <tr>
          <td></td>
          <th>Facial Exp</th>
          <td>
            <input name="screens[${screenStatus.index}].facialExpression" value="${command.screens[screenStatus.index].facialExpression}" style="width:98%">
          </td>
          <th>Unknown</th>
          <td>
            <input name="screens[${screenStatus.index}].unknown" value="${command.screens[screenStatus.index].unknown}" style="width:98%">
          </td>
        </tr>
        <tr>
          <td>i</td><td colspan="4"><hr/></td>
        </tr>
  </c:forEach>
      </tbody>
    </table>
  </div>
  </form:form>
  <button onclick="submitCutsceneEditForm('#cutsceneEditForm_${cutsceneId}')">Save</button>
</div>
