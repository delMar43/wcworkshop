<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="scrollablePane">
  <h2>
    Campaign ${campaign}, 
    <%-- a href="javascript:openSeriesEditor('${campaign}', ${seriesIndex +0})">Series ${seriesIndex +1}</a>,
    <a href="javascript:openMissionEditor('${campaign}', ${seriesIndex +0}, ${missionIndex +0})">Mission ${missionIndex +1}</a>: ${cutsceneIndex} --%>
  </h2>
  
  <form:form action="saveCutscene.html" method="POST">
  <div>
    <h3>Screens</h3>
    <table>
      <thead>
        <tr>
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
          <td>${screenStatus.count}</td>
          <td>
            <select name="screens[screenStatus.index].foreground">
              <c:forEach items="${foregrounds}" var="foreground">
                <option label="${foreground.label}" value="${foreground.value}" <c:if test="${foreground.value == command.screens[screenStatus.index].foreground}">selected="selected"</c:if>></option>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="screens[screenStatus.index].background">
              <c:forEach items="${backgrounds}" var="background">
                <option label="${background.label}" value="${background.value}" <c:if test="${background.value == command.screens[screenStatus.index].background}">selected="selected"</c:if>></option>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="screens[screenStatus.index].textColor">
              <c:forEach items="${textColors}" var="textColor">
                <option label="${textColor.label}" value="${textColor.value}" <c:if test="${textColor.value == command.screens[screenStatus.index].textColor}">selected="selected"</c:if>></option>
              </c:forEach>
            </select>
          </td>
          <td>${screen.commands}</td>
          <td>${screen.text}</td>
          <td>${screen.facialExpression}</td>
          <td>${screen.phonetic}</td>
          <td>${screen.unknown}</td>
        </tr>
  </c:forEach>
      </tbody>
    </table>
  </div>
  </form:form>
</div>