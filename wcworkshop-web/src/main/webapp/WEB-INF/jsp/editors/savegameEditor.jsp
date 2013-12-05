<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <%@ include file="../scriptsAndStyles.jsp" %>
    <script type="text/javascript">
      var savegameTabs;
      
      $(function() {
        savegameTabs = $("#savegameTabs").tabs();
      });
    </script>
  </head>
  <body>
    <div class="caption">Special Thanks to David S. Raley for creating WCSAV, which was used to identify most of the savegame data.</div>
      
    <form:form action="savegame.wld" method="POST" id="savegameForm">
      <div id="savegameTabs">
        <ul>
          <c:forEach items="${command.savegames}" var="savegame" varStatus="savegameStatus">
              <li class="editorTab" id="tab_savegame${savegameStatus.count}">
                <a href="#tab-savegame${savegameStatus.count}">[${savegameStatus.count}] ${savegame.name}</a>
              </li>
          </c:forEach>
        </ul>
        <c:forEach items="${command.savegames}" var="savegame" varStatus="savegameStatus">
          <div id="tab-savegame${savegameStatus.count}" class="editorTabContent scrollablePane" style="margin-top:40px">
            <fieldset>
              <legend>Game Progress</legend>
              <span style="float:left">
                <div>
                  <span>Name:</span><span><input name="savegames[${savegameStatus.index}].name" value="${savegame.name}"></input></span>
                </div>
                <div>
                  <span>Enabled:</span><span><input name="savegames[${savegameStatus.index}].occupied" value="${savegame.occupied}" size="1"></input></span>
                  <span>Campaign:</span><span><input name="savegames[${savegameStatus.index}].campaign" value="${savegame.campaign}" size="2"></input></span>
                </div>
                <div>
                  <span>Series:</span><span><input name="savegames[${savegameStatus.index}].series" value="${savegame.series}" size="2"></input>
                  <span>Mission:</span><span><input name="savegames[${savegameStatus.index}].mission" value="${savegame.mission}" size="2"></input></span>
                </div>
                <div>
                  <span>Series Count:</span><span><input name="savegames[${savegameStatus.index}].seriesCount" value="${savegame.seriesCount}"></input></span>
                </div>
                <div>
                  <span>Year:</span><span><input name="savegames[${savegameStatus.index}].year" value="${savegame.year}" size="4"></input>
                  <span>Day:</span><span><input name="savegames[${savegameStatus.index}].day" value="${savegame.day}" size="3"></input></span>
                </div>
                <div>
                  <span>Victory Points:</span><span><input name="savegames[${savegameStatus.index}].victory" value="${savegame.victory}" size="3"></input></span>
                </div>
              </span>
              <span style="float:left">
                Bronze Stars: <input name="savegames[${savegameStatus.index}].bronzeStars" value="${savegame.bronzeStars}" size="3"></input><br/>
                Silver Stars: <input name="savegames[${savegameStatus.index}].silverStars" value="${savegame.silverStars}" size="3"></input><br/>
                Gold Stars: <input name="savegames[${savegameStatus.index}].goldStars" value="${savegame.goldStars}" size="3"></input><br/>
                Pewter Planet: <input name="savegames[${savegameStatus.index}].pewterPlanet" value="${savegame.pewterPlanet}" size="3"></input><br/>
                Golden Sun: <input name="savegames[${savegameStatus.index}].goldenSun" value="${savegame.goldenSun}" size="3"></input><br/>
                Promotion: <input name="savegames[${savegameStatus.index}].promotion" value="${savegame.promotion}" size="3"></input>
              </span>
              <span style="float:left">
                Bhurak: <input name="savegames[${savegameStatus.index}].ace1" value="${savegame.ace1}" size="3"></input><br/>
                Dakhath: <input name="savegames[${savegameStatus.index}].ace2" value="${savegame.ace2}" size="3"></input><br/>
                Khajja: <input name="savegames[${savegameStatus.index}].ace3" value="${savegame.ace3}" size="3"></input><br/>
                Baktosh: <input name="savegames[${savegameStatus.index}].ace4" value="${savegame.ace4}" size="3"></input>
              </span>
            </fieldset>
            
            <fieldset>
              <legend>Scoreboard</legend>
              <table class="scoreboardTable">
                <thead>
                  <tr>
                    <th>Rank</th>
                    <th>Name</th>
                    <th>Callsign</th>
                    <th>Sorties</th>
                    <th>Kills</th>
                    <%-- th>Status</th --%>
                    <th>Unknown 1</th>
                    <th>Unknown 2</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${savegame.scoreboardEntries}" var="entry" varStatus="entryStatus">
                    <tr>
                      <td><input name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].rank" value="${entry.rank}"></td>
                      <td><input name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].name" value="${entry.name}"></td>
                      <td><input name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].callsign" value="${entry.callsign}"></td>
                      <td><input name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].sorties" value="${entry.sorties}"></td>
                      <td><input name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].kills" value="${entry.kills}"></td>
                      <%-- td><input name="savegames[${savegameStatus.index}].pilotStatus[${entryStatus.index}]" value="${savegame.pilotStatus[entryStatus.index]}"></td --%>
                      <td><input name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].unknown1" value="${entry.unknown1}"></td>
                      <td><input name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].unknown2" value="${entry.unknown2}"></td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </fieldset>
          </div>
        </c:forEach>
      </div>
      <button type="submit">Save</button>&nbsp;<button type="reset">Reset</button>
    </form:form>
  </body>
</html>