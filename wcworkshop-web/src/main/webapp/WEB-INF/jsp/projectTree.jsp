<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="projectTree" id="projectTree">
  <c:forEach items="${projectNodes}" var="projectNode" varStatus="projectStatus">
    <li class="folder <c:if test="${projectStatus.first}">active focus</c:if> project=${projectNode.id}">${projectNode.label}
      <ul>
        <c:forEach items="${projectNode.children}" var="seriesNode" varStatus="seriesNodeStatus">
          <li class="folder series=${seriesNode.id} project=${projectNode.id} label=${seriesNode.tabLabel}">${seriesNode.label}
            <ul>
            <c:forEach items="${seriesNode.children}" var="missionNode" varStatus="missionNodeStatus">
              <li class="folder mission=${missionNode.id} project=${projectNode.id} label=${missionNode.tabLabel}">${missionNode.label}
                <ul>
                  <li class="folder">Cutscenes
                    <ul>
                      <li class="cutscene=briefing mission=${missionNode.id} project=${projectNode.id} label=${seriesNode.tabLabel}/${missionNode.tabLabel}/Briefing">Briefing</li>
                      <li class="cutscene=debriefing mission=${missionNode.id} project=${projectNode.id} label=${seriesNode.tabLabel}/${missionNode.tabLabel}/Debriefing">Debriefing</li>
                      <li class="cutscene=shotglass mission=${missionNode.id} project=${projectNode.id} label=${seriesNode.tabLabel}/${missionNode.tabLabel}/Shotglass">Shotglass</li>
                      <li class="cutscene=left_seat mission=${missionNode.id} project=${projectNode.id} label=${seriesNode.tabLabel}/${missionNode.tabLabel}/Left">Left</li>
                      <li class="cutscene=right_seat mission=${missionNode.id} project=${projectNode.id} label=${seriesNode.tabLabel}/${missionNode.tabLabel}/Right">Right</li>
                    </ul>
                  </li>
                  <li class="folder">Nav Points
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
