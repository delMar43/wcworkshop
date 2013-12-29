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
    <link href="<%=request.getContextPath() %>/styles/ui-fancytree.css" rel="stylesheet" type="text/css">
    <script src="<%=request.getContextPath() %>/scripts/jquery.fancytree-all.min.js" type="text/javascript"></script>
    <script>
      var editorTabs;
      var projectTabs;
      var openTabs = {};
      var openProjectTabs = {};
      
      $(function() {
        var layout = $("body").layout({ applyDefaultStyles: true });
        layout.sizePane("west", 280);
        
        projectTabs = $("#projectTabs").tabs({
          beforeLoad: function( event, ui ) {
            if ( ui.tab.data( "loaded" ) ) {
                event.preventDefault();
                return;
            }
     
            ui.jqXHR.success(function() {
                ui.tab.data( "loaded", true );
            });
          }
        });
        
        editorTabs = $("#editorTabs").tabs({
          beforeLoad: function( event, ui ) {
            if ( ui.tab.data( "loaded" ) ) {
                event.preventDefault();
                return;
            }
     
            ui.jqXHR.success(function() {
                ui.tab.data( "loaded", true );
            });
          }
        });
        
        //$(".ui-tabs-nav").sortable();

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
          if (!confirm("Really close?")) {
            return;
          }
          var li = $( this ).closest( "li" );
          var panelId = li.remove().attr( "aria-controls" );
          $( "#" + panelId ).remove();
          projectTabs.tabs( "refresh" );
          delete openProjectTabs[li.attr("id")];
        });
  
        loadProjectTree();
      });
      
      var loadProjectTree = function() {
        $("#tab-projects").fancytree({
          source: {
            url: "<%=request.getContextPath()%>/projectTree.json"
          },
          click: function(event, data) {
            var extraClass = data.node.extraClasses;
            var nodeData = data.node.data;
            //alert(nodeData);
            /*var infoArray = extraClass.split(" ");
            var infoMap = {};

            var type;
            
            var infoIndex = 0;
            infoArray.forEach(function(keyValue) {
              var keyValueArray = keyValue.split("=");
              var key = keyValueArray[0];
              var value = keyValueArray[1];
              
              if (infoIndex++ == 0) {
                type = key;
              }
              
              infoMap[key] = value;
            });
            
            if (type == "project") {
              
            } else if (type == "series") {
              openSeriesEditor(infoMap["project"], infoMap["series"],  infoMap["label"]);
            } else if (type == "mission") {
              //openMissionEditor(infoMap["project"], infoMap["mission"], infoMap["label"]);
            } else if (type == "cutscene") {
              openCutsceneEditor(infoMap["project"], infoMap["mission"], infoMap["cutscene"], infoMap["label"])
            }*/
          }
        });
      };

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
          $("<li id='" + id + "' class='" + tabClass + "'><a href='" + href + "'>" + label.replace(/_/g, ' ') + "</a> <span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>").appendTo(ul);
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
      
      var openSeriesEditor = function(projectId, seriesId, label) {
    	var key = "C" + projectId + "S" + seriesId;
        addTab(key, label, "<%=request.getContextPath()%>/seriesEditor.html?projectId=" + projectId + "&seriesId=" + seriesId, editorTabs, "editorTab");
      };
      
      var openMissionEditor = function(projectId, missionId, label) {
    	var key = missionId;
        addTab(key, label, "<%=request.getContextPath()%>/missionEditor.html?projectId=" + projectId + "&missionId=" + missionId, editorTabs, "editorTab");
      };
      
      var openCutsceneEditor = function(projectId, missionId, cutsceneIndex, label) {
    	var key = "C" + projectId + "M" + missionId + "C" + cutsceneIndex;
        addTab(key, label, "<%=request.getContextPath()%>/cutsceneEditor.html?projectId=" + projectId + "&missionId=" + missionId + "&cutsceneType=" + cutsceneIndex, editorTabs, "editorTab");
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
      
      var openDownloadTab = function() {
        addTab('download', 'Download', '<%=request.getContextPath()%>/downloads.html', editorTabs, 'editorTab');
      }
      
      var openProjectEditDialog = function() {
        $("<div></div>").load("<%=request.getContextPath()%>/projectForm.html").dialog({
          height: 300,
          width: 400,
          buttons: {
            "Create": function() {
		      submitProjectEditForm();
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
      
      var openProjectUploadDialog = function() {
        $("<div></div>").load("<%=request.getContextPath()%>/uploadForm.html").dialog({
          height: 300,
          width: 400,
          buttons: {
            "Create": function() {
		      submitProjectUploadForm();
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
      
      var generateBinaryFiles = function(project) {
        $.ajax({
          url: "<%=request.getContextPath()%>/generateProject.html",
          data: "projectId=" + project,
          success: function(response) {
            $("#downloadTabContent").html(response);
          }
        })
      }
      
      var submitAjaxForm = function(formId) {
        var ajaxUrl = $(formId).attr("action");
        var ajaxData = $(formId).serialize();
        $.ajax({
          type: "POST",
          url: ajaxUrl,
          data: ajaxData,
          success: function(response) {
            alert("done");
          }
        });
      }
      
      var removeScreen = function(screenId) {
        $('.' + screenId).remove();
      }
      
      var reloadProjectTree = function() {
        loadProjectTree();
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
        <!-- button onclick="openProjectEditDialog()" id="newCampaignButton" title="Start a new campaign">New</button>
        <button id="openCampaignButton" title="Open an existing campaign">Open</button>
        <button id="closeCampaignButton" title="Close current campaign">Close</button>
        <button onclick="openProjectUploadDialog()" id="uploadCampaignButton" title="Upload campaign files from filesystem">Upload</button>
        <button id="importCampaignButton" title="Import campaign shared by another user">Import</button>
        <button id="shareCampaignButton" title="Share this campaign">Share</button -->
      </div>
      <div id="projectTabs" class="scrollableTab">
        <ul>
          <li><a href="#tab-projects">My Projects</a>
        </ul>
        <div id="tab-projects">
        </div>
      </div>
    </div>
  </body>
</html>