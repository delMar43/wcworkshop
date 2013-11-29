<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="scrollablePane">
  <h2>
    <a href="javascript:openSeriesEditor(${seriesIndex +0})">Series ${seriesIndex +1}</a>,
    <a href="javascript:openMissionEditor(${seriesIndex +0}, ${missionIndex +0})">Mission ${missionIndex +1}</a>: ${cutscene.title}
  </h2>
  <div>
    <h3>First Line</h3>
    <table>
      <tbody>
        <tr>
          <th>Foreground</th>
          <td>${cutsceneUtil.getForeground(cutscene.foreground)}</td>
        </tr>
        <tr>
          <th>Background</th>
          <td>${cutsceneUtil.getBackground(cutscene.background)}</td>
        </tr>
        <tr>
          <th>Text Color</th>
          <td>${cutsceneUtil.getTextColor(cutscene.textColor)}</td>
        </tr>
        <tr>
          <th>Unknown 1</th>
          <td>${cutsceneUtil.hexString(cutscene.unknown1)}</td>
        </tr>
        <tr>
          <th>Unknown 2</th>
          <td>${cutsceneUtil.hexString(cutscene.unknown2)}</td>
        </tr>
        <tr>
          <th>Commands</th>
          <td>${cutsceneUtil.getConditionString(cutscene.condition)}</td>
        </tr>
        <tr>
          <th>Text</th>
          <td>${cutscene.firstLine}</td>
        </tr>
        <tr>
          <th>Phonetic</th>
          <td>${cutscene.firstLinePhonetic}</td>
        </tr>
      </tbody>
    </table>
  </div>
  
  <div>
  Known fact:<br/>
  Some commands may have parameters.<br/>
  A parameter comes immediately after the command, without 0x2c in between.<br/>
  Parameters are not yet displayed as such here and thus are shown as Unknown (but they aren't)
  </div>
  
  <div>
    <h3>Subsequent Lines</h3>
    <table>
      <thead>
        <tr>
          <th>Commands</th>
          <th>Text</th>
          <th>Facial expression</th>
          <th>Phonetic</th>
        </tr>
      </thead>
      <tbody>
  <c:forEach items="${cutscene.cutsceneLines}" var="line" varStatus="lineStatus">
        <tr>
          <td>${cutsceneUtil.getConditionString(line.commandBytes)}</td>
          <td>${line.text}</td>
          <td>${line.facialExpressions}</td>
          <td>${line.phonetic}</td>
        </tr>
  </c:forEach>
      </tbody>
    </table>
  </div>
</div>