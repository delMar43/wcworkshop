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
          <td>${cutsceneUtil.getForeground(screen.foreground)}</td>
          <td>${cutsceneUtil.getBackground(screen.background)}</td>
          <td>${cutsceneUtil.getTextColor(screen.textColor)}</td>
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