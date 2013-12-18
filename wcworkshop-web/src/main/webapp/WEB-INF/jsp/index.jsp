<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html>
  <head>
    <title>WC Workshop</title>
    <meta name="description" content="Wing Commander Campaign Editor" />
    <meta name="keywords" content="Wing Commander, Secret Missions, WC, SM, SM1, SM2, WC2, SO1, SO2, WCA, Academy, Armada, Kilrathi, Tiger's Claw, Victory, Concordia, Caernavon" />
    <%@ include file="scriptsAndStyles.jsp" %>
    <script src="<%=request.getContextPath() %>/scripts/jquery.layout-latest.min.js"></script>
    <script src="<%=request.getContextPath() %>/scripts/jquery.jstree.js"></script>
    <script>
      var editorTabs;
      var projectTabs;
      var openTabs = {};
      var openProjectTabs = {};
      
      $(function() {
        var layout = $("body").layout({ applyDefaultStyles: true });
        layout.sizePane("west", 280);
        projectTabs = $("#projectTabs").tabs();
        editorTabs = $("#editorTabs").tabs();
        
        $(".ui-tabs-nav").sortable();

        <%--
        $.ajax({
          url: "<%=request.getContextPath()%>/campaignContentsTree.html?campaign=000",
          success: function(data, textStatus, jqXHR) {
            $("#tab-campaignContents_000").html(data);
          }
        });

        $.ajax({
          url: "<%=request.getContextPath()%>/campaignContentsTree.html?campaign=001",
          success: function(data, textStatus, jqXHR) {
            $("#tab-campaignContents_001").html(data);
          }
        });
        
        $.ajax({
          url: "<%=request.getContextPath()%>/campaignContentsTree.html?campaign=002",
          success: function(data, textStatus, jqXHR) {
            $("#tab-campaignContents_002").html(data);
          }
        });
        --%>
        
        // close icon: removing the tab on click
        editorTabs.delegate( "span.ui-icon-close", "click", function() {
          var li = $( this ).closest( "li" );
          var panelId = li.remove().attr( "aria-controls" );
          $( "#" + panelId ).remove();
          editorTabs.tabs( "refresh" );
          delete openTabs[li.attr("id")];
        });
        
        // close icon: removing the tab on click
        projectTabs.delegate( "span.ui-icon-close", "click", function() {
          var li = $( this ).closest( "li" );
          var panelId = li.remove().attr( "aria-controls" );
          $( "#" + panelId ).remove();
          projectTabs.tabs( "refresh" );
          delete openProjectTabs[li.attr("id")];
        });
        
      });

      // actual addTab function: adds new tab using the input from the form above
      var addStaticTab = function(key, label, content, tabContainer, tabClass) {
        if (!tabAlreadyOpen(key)) {
          var li = $("<li id='tab_" + key + "' class='" + tabClass + "'><a href='#tab-" + key + "'>" + label + "</a> <span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>");
     
          tabContainer.find( ".ui-tabs-nav" ).append( li );
          tabContainer.append( "<div id='tab-" + key + "'>" + content + "</div>" );
          tabContainer.tabs( "refresh" );
          openTabs['tab_' + key] = true;
        }
        switchTab(key, tabContainer, tabClass);
      }
      
      var addTab = function(key, label, href, tabContainer, tabClass) {
        if (!tabAlreadyOpen(key)) {
          var id = 'tab_' + key;
          var ul = tabContainer.find(".ui-tabs-nav");
          $("<li id='" + id + "' class='" + tabClass + "'><a href='" + href + "'>" + label+ "</a> <span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>").appendTo(ul);
          tabContainer.tabs("refresh");
          openTabs[id] = true;
        }
        switchTab(key, tabContainer, tabClass);
      };
      
      var switchTab = function(key, tabContainer, tabClass) {
        var id = "#tab_" + key;
        var listItem = $(id);
        var index = $("li." + tabClass).index(listItem);
        tabContainer.tabs( "option", "active", index );
      };
      
      var tabAlreadyOpen = function(key) {
        return ("tab_" + key) in openTabs;
      }
      
      var openSeriesEditor = function(campaign, seriesIndex) {
    	var key = "C" + campaign + "S" + (seriesIndex+1);
    	var label = campaign + " Series " + (seriesIndex +1);
        addTab(key, label, "<%=request.getContextPath()%>/seriesEditor.html?campaign=" + campaign + "&seriesIndex=" + seriesIndex, editorTabs, "editorTab");
      };
      
      var openMissionEditor = function(campaign, seriesIndex, missionIndex) {
    	var key = "C" + campaign + "S" + (seriesIndex+1) + "M" + (missionIndex+1);
    	var label = campaign + " S" + (seriesIndex+1) + " M" + (missionIndex+1);
        addTab(key, label, "<%=request.getContextPath()%>/missionEditor.html?campaign=" + campaign + "&seriesIndex=" + seriesIndex + "&missionIndex=" + missionIndex, editorTabs, "editorTab");
      };
      
      var openCutsceneEditor = function(campaign, seriesIndex, missionIndex, cutsceneIndex) {
    	var key = "C" + campaign + "S" + (seriesIndex+1) + "M" + (missionIndex+1) + "C" + cutsceneIndex;
    	var label = campaign + " S" + (seriesIndex+1) + " M" + (missionIndex+1) + " " + cutsceneIndex;
        addTab(key, label, "<%=request.getContextPath()%>/cutsceneEditor.html?campaign=" + campaign + "&seriesIndex=" + seriesIndex + "&missionIndex=" + missionIndex + "&cutsceneIndex=" + cutsceneIndex, editorTabs, "editorTab");
      };
      
      var openNavPointEditor = function(campaign, seriesIndex, missionIndex, navPointIndex) {
    	var key = "C" + campaign + "S" + (seriesIndex+1) + "M" + (missionIndex+1) + "N" + navPointIndex;
    	var label = campaign + " S" + (seriesIndex+1) + " M" + (missionIndex+1) + " Nav " + navPointIndex;
        addTab(key, label, "<%=request.getContextPath()%>/navPointEditor.html?campaign=" + campaign + "&seriesIndex=" + seriesIndex + "&missionIndex=" + missionIndex + "&navPointIndex=" + navPointIndex, editorTabs, "editorTab");
      };
      
      var openSavegameEditor = function() {
        addStaticTab("savegameEditor", "Savegame Generator", "<iframe class='framed' src='<%=request.getContextPath()%>/savegameEditor.html'></iframe>", editorTabs, "editorTab");
      };
      
      var openCredits = function() {
        addStaticTab("credits", "Credits", "<iframe class='framed' src='<%=request.getContextPath()%>/credits.html'></iframe>", editorTabs, "editorTab");
      }
      
      var openProjectEditDialog = function() {
        $("<div></div>").load("<%=request.getContextPath()%>/projectForm.html").dialog({
          height: 300,
          width: 400,
          buttons: {
            "Create": function() {
		      submitForm();
              $(this).dialog("close");
            },
            Cancel: function() {
              $(this).dialog("close");
            }
          },
          close: function() {
            $(this).dialog ('destroy').remove ();
          }
        });
      }
    </script>
  </head>
  
  <body>
    <div class="ui-layout-north" style="position:relative;clear: both;">
      <%@include file="north.jsp" %>
    </div>
    <div class="ui-layout-center">
      <div id="editorTabs" class="scrollableTab">
        <ul>
        </ul>
      </div>
    </div>
    <div class="ui-layout-west">
      <div id="projectViewToolbar">
        <button onclick="openProjectEditDialog()" id="newCampaignButton" title="Start a new campaign">New</button>
        <button id="openCampaignButton" title="Open an existing campaign">Open</button>
        <button id="closeCampaignButton" title="Close current campaign">Close</button>
        <button id="uploadCampaignButton" title="Upload campaign files from filesystem">Upload</button>
        <button id="importCampaignButton" title="Import campaign shared by another user">Import</button>
        <button id="shareCampaignButton" title="Share this campaign">Share</button>
      </div>
      <div id="projectTabs" class="scrollableTab">
        <ul>
          <!-- li><a href="#tab-allContents">All</a></li>
          <li><a href="#tab-campaignContents_000">WC</a><span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>
          <li><a href="#tab-campaignContents_001">SM1</a><span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>
          <li><a href="#tab-campaignContents_002">SM2</a><span class='ui-icon ui-icon-close' role='presentation'>Close</span></li -->
        </ul>
        <!-- div id="tab-allContents"></div>
        <div id="tab-campaignContents_000"></div>
        <div id="tab-campaignContents_001"></div>
        <div id="tab-campaignContents_002"></div -->
      </div>
    </div>
  </body>
</html>