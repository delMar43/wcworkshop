<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="../scriptsAndStyles.jsp" %>
    <%@ include file="../validation.jsp" %>
    <script type="text/javascript">
      var savegameTabs;
      
      $(function() {
        savegameTabs = $("#savegameTabs").tabs();
        $("#savegameForm").validationEngine();
        
        <%-- $(".savegameStatus").each(function(index, elem) {
          var jElem = $(elem);
          jElem.change(toggleSavegame(jElem.attr('rel')));
        }); --%>
      });
      
      <%-- var toggleSavegame = function(formId) {
        $(formId).children().attr("disabled", "disabled");
      } --%>
    </script>
  </head>
  <body>
    <div class="caption" style="margin-bottom: 10px">Special Thanks to David S. Raley for creating WCSAV, which was used to identify most of the savegame data.</div>
    
    <form:form action="savegame.wld" method="POST" id="savegameForm">
      <button type="submit">Generate and Download</button>&nbsp;<button type="reset">Reset Field Contents</button>
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
            <span>Status:</span>
            <span>
              <input type="radio" name="savegames[${savegameStatus.index}].occupied" value="0" <c:if test="${!savegame.enabled}">checked="checked"</c:if> size="1">Inactive</input>
              <input class="savegameStatus" rel="tab-savegame${savegameStatus.index}" type="radio" name="savegames[${savegameStatus.index}].occupied" value="1" <c:if test="${savegame.enabled}">checked="checked"</c:if> size="1">Active</input>
            </span>
            <span class="leftMargin">Name:</span><span><input class="validate[required]" maxlength="16" name="savegames[${savegameStatus.index}].name" value="${savegame.name}"></input></span>
            <fieldset>
              <legend>Game Progress</legend>
              <span style="float:left">
                <table class="withoutBorders ui-widget">
                  <tbody class="withoutBorders">
                    <tr><td class="withoutBorders">Campaign:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].campaign" value="${savegame.campaign}" size="2"></input></td></tr>
                    <tr><td class="withoutBorders">Series (1-13):</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[1],max[13]]" maxlength="2" name="savegames[${savegameStatus.index}].series" value="${savegame.series}" size="2"></input></td></tr>
                    <tr><td class="withoutBorders">Mission (0-3):</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0],max[3]]" maxlength="1" name="savegames[${savegameStatus.index}].mission" value="${savegame.mission}" size="2"></input></td></tr>
                    <tr><td class="withoutBorders">Series Count:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].seriesCount" value="${savegame.seriesCount}" size="2"></input></td></tr>
                  </tbody>
                </table>
              </span>
              <span style="float:left">
                <table class="withoutBorders ui-widget">
                  <tbody class="withoutBorders">
                    <tr><td class="withoutBorders">Bronze Stars:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].bronzeStars" value="${savegame.bronzeStars}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Silver Stars:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].silverStars" value="${savegame.silverStars}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Gold Stars:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].goldStars" value="${savegame.goldStars}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Pewter Planet:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].pewterPlanet" value="${savegame.pewterPlanet}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Golden Sun:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].goldenSun" value="${savegame.goldenSun}" size="3"></input></td></tr>
                  </tbody>
                </table>
              </span>
              <span style="float:left">
                <table class="ui-widget withoutBorders">
                  <tbody>
                    <tr><td class="withoutBorders">Promotion Points:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="5" name="savegames[${savegameStatus.index}].promotion" value="${savegame.promotion}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Victory Points:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0],max[65535]]" maxlength="5" name="savegames[${savegameStatus.index}].victory" value="${savegame.victory}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Year:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="4" name="savegames[${savegameStatus.index}].year" value="${savegame.year}" size="4"></input></td></tr>
                    <tr><td class="withoutBorders">Day:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="3" name="savegames[${savegameStatus.index}].day" value="${savegame.day}" size="3"></input></td></tr>
                  </tbody>
                </table>
              </span>
              <span style="float:left">
                <table class="withoutBorders ui-widget">
                  <tbody class="withoutBorders">
                    <tr><td class="withoutBorders">Bhurak:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].ace1" value="${savegame.ace1}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Dakhath:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].ace2" value="${savegame.ace2}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Khajja:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].ace3" value="${savegame.ace3}" size="3"></input></td></tr>
                    <tr><td class="withoutBorders">Baktosh:</td><td class="withoutBorders"><input class="validate[required,custom[integer],min[0]]" maxlength="2" name="savegames[${savegameStatus.index}].ace4" value="${savegame.ace4}" size="3"></input></td></tr>
                  </tbody>
                </table>
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
                      <td><input class="validate[required,custom[integer],min[0],max[4]]" name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].rank" value="${entry.rank}"></td>
                      <td><input class="validate[required]" maxlength="14" name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].name" value="${entry.name}"></td>
                      <td><input class="validate[required]" maxlength="14" name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].callsign" value="${entry.callsign}"></td>
                      <td><input class="validate[required,custom[integer],min[0]]" name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].sorties" value="${entry.sorties}"></td>
                      <td><input class="validate[required,custom[integer],min[0]]" name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].kills" value="${entry.kills}"></td>
                      <%-- td><input name="savegames[${savegameStatus.index}].pilotStatus[${entryStatus.index}]" value="${savegame.pilotStatus[entryStatus.index]}"></td --%>
                      <td><input class="validate[required,custom[integer],min[0]]" name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].unknown1" value="${entry.unknown1}"></td>
                      <td><input class="validate[required,custom[integer],min[0]]" name="savegames[${savegameStatus.index}].scoreboardEntries[${entryStatus.index}].unknown2" value="${entry.unknown2}"></td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </fieldset>
          </div>
        </c:forEach>
      </div>
    </form:form>
  </body>
</html>