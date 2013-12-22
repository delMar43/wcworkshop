<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="projectTree" id="projectTree">
  <c:forEach items="${projectNodes}" var="projectNode" varStatus="projectStatus">
    <li class="project=${projectNode.id}">${projectNode.label}
      <ul>
        <c:forEach items="${projectNode.seriesNodes}" var="seriesNode" varStatus="seriesNodeStatus">
          <li class="series=${seriesNode.id} project=${projectNode.id}">${seriesNode.label}
            <ul>
            <c:forEach items="${seriesNode.missionNodes}" var="missionNode" varStatus="missionNodeStatus">
              <li class="mission=${missionNode.id} project=${projectNode.id}">${missionNode.label}
                <ul>
                  <li>Cutscenes
                    <ul>
                      <li class="mission=${missionNode.id} cutscene=briefing project=${projectNode.id}">Briefing</li>
                      <li class="mission=${missionNode.id} cutscene=debriefing project=${projectNode.id}">Debriefing</li>
                      <li class="mission=${missionNode.id} cutscene=shotglass project=${projectNode.id}">Shotglass</li>
                      <li class="mission=${missionNode.id} cutscene=left project=${projectNode.id}">Left</li>
                      <li class="mission=${missionNode.id} cutscene=right project=${projectNode.id}">Right</li>
                    </ul>
                  </li>
                  <li>Nav Points
                    <ul>
                      <c:forEach items="${missionNode.navPointNodes}" var="navPointNode" varStatus="navPointStatus">
                        <li class="navPoint=${navPointNode.id} project=${projectNode.id}">${navPointNode.label}</li>
                      </c:forEach>
                    </ul>
                  </li>
                </ul>
              </li>
            </c:forEach>
            </ul>
          </li>
        </c:forEach>
      </ul>
    </li>
  </c:forEach>
</ul>
