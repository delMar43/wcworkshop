<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

  <tr class="screen_${command.missionId}_${screenIndex}">
    <td title="Order of current line">
      <input name="screens[${screenIndex}].sequence" value="${command.screens[screenIndex].sequence}" size="2" />
    </td>
    <th>Commands</th>
    <td>
      <c:forEach items="${screen.commands}" var="screenCommand" varStatus="screenCommandStatus">
        cmd:${screenCommand.codeString}, param:${screenCommand.parameterString}
        <input type="hidden" name="screens[${screenIndex}].commands[${screenCommandStatus.index}].codeString" value="${command.screens[screenIndex].commands[screenCommandStatus.index].codeString}">
        <input type="hidden" name="screens[${screenIndex}].commands[${screenCommandStatus.index}].parameterString" value="${command.screens[screenIndex].commands[screenCommandStatus.index].parameterString}">
      </c:forEach>
    </td>
    <th>Text</th>
    <td rowspan="2">
      <textarea name="screens[${screenIndex}].text" cols="80" rows="2">${command.screens[screenIndex].text}</textarea>
    </td>
  </tr>
  <tr class="screen_${command.missionId}_${screenIndex}">
    <td title="not implemented yet">up</td>
    <th>Foreground</th>
    <td>
      <select name="screens[${screenIndex}].foreground" style="width:98%">
        <c:forEach items="${foregrounds}" var="foreground">
          <c:if test="${foreground.value != -2}">
            <option label="${foreground.label}" value="${foreground.value}" <c:if test="${foreground.value == command.screens[screenIndex].foreground}">selected="selected"</c:if>></option>
          </c:if>
        </c:forEach>
      </select>
    </td>
  </tr>
  <tr class="screen_${command.missionId}_${screenIndex}">
    <td title="Delete current line"><a href="javascript:removeScreen('screen_${command.missionId}_${screenIndex}')">del</a></td>
    <th>Background</th>
    <td>
      <select name="screens[${screenIndex}].background" style="width:98%">
        <c:forEach items="${backgrounds}" var="background">
          <option label="${background.label}" value="${background.value}" <c:if test="${background.value == command.screens[screenIndex].background}">selected="selected"</c:if>></option>
        </c:forEach>
      </select>
    </td>
    <th>Phonetics</th>
    <td rowspan="2">
      <textarea name="screens[${screenIndex}].phonetic" cols="80" rows="2">${command.screens[screenIndex].phonetic}</textarea>
    </td>
  </tr>
  <tr class="screen_${command.missionId}_${screenIndex}">
    <td title="not implemented yet">down</td>
    <th>Text Color</th>
    <td>
      <select name="screens[${screenIndex}].textColor" style="width:98%">
        <c:forEach items="${textColors}" var="textColor">
          <option label="${textColor.label}" value="${textColor.value}" <c:if test="${textColor.value == command.screens[screenIndex].textColor}">selected="selected"</c:if>></option>
        </c:forEach>
      </select>
    </td>
  </tr>
  <tr class="screen_${command.missionId}_${screenIndex}">
    <td></td>
    <th>Facial Exp</th>
    <td>
      <input name="screens[${screenIndex}].facialExpression" value="${command.screens[screenIndex].facialExpression}" style="width:98%">
    </td>
    <th>Unknown</th>
    <td>
      <input name="screens[${screenIndex}].unknown" value="${command.screens[screenIndex].unknown}" style="width:98%">
    </td>
  </tr>
  <tr class="screen_${command.missionId}_${screenIndex}">
    <td colspan="5" style="border-left: 0px none;border-right: 0px none;">&nbsp;</td>
  </tr>
