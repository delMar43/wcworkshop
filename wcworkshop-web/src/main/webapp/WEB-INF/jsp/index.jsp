<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>WC Workshop</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/base/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/default/style.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/wcworkshop.css" />
    <script src="<%=request.getContextPath() %>/scripts/jquery-2.0.3.min.js"></script>
    <script src="<%=request.getContextPath() %>/scripts/jquery-ui-1.10.3.min.js"></script>
    <script src="<%=request.getContextPath() %>/scripts/jquery.layout-latest.min.js"></script>
    <script src="<%=request.getContextPath() %>/scripts/jquery.jstree.js"></script>
    <script>
      var editorTabs;
      var openTabs = {};
      
      $(function() {
        var layout = $("body").layout({ applyDefaultStyles: true });
        layout.sizePane("west", 280);
        $("#projectViewTabs").tabs();
        editorTabs = $("#editorTabs").tabs();
        
        $(".ui-tabs-nav").sortable();
        
        $.ajax({
          url: "<%=request.getContextPath()%>/campaignContentsTree.html",
          success: function(data, textStatus, jqXHR) {
            $("#tab-campaignContents").html(data);
            /*
            $("#campaignTree").jstree({
              "plugins" : ["themes","html_data","ui","crrm","hotkeys"],
              "types": {
                "default":{
                  "max_children":-2,
                  "max_depth":-2,
                  "valid_children":"all"
                }
              }
            });
            */
          }
        });
        
        /*
        $.ajax({
          url: "<%=request.getContextPath()%>/savegameEditor.html",
          success: function(data, textStatus, jqXHR) {
            $("#tab-savegameEditor").html(data);
          }
        });
        */
        
        // close icon: removing the tab on click
        editorTabs.delegate( "span.ui-icon-close", "click", function() {
          var li = $( this ).closest( "li" );
          var panelId = li.remove().attr( "aria-controls" );
          $( "#" + panelId ).remove();
          editorTabs.tabs( "refresh" );
          delete openTabs[li.attr("id")];
        });

      });

      var addTab = function(key, label, href) {
        var ul = editorTabs.find(".ui-tabs-nav");
        $("<li id='tab_" + key + "' class='editorTab'><a href='" + href + "'>" + label+ "</a> <span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>").appendTo(ul);
        editorTabs.tabs("refresh");
        openTabs['tab_' + key] = true;
        
        switchTab(key);
      };
      
      var switchTab = function(key) {
        var id = "#tab_" + key;
        var listItem = $(id);
        var index = $("li.editorTab").index(listItem);
        editorTabs.tabs( "option", "active", index );
      };
      
      var tabAlreadyOpen = function(key) {
        return ("tab_" + key) in openTabs;
      }
      
      var openSeriesEditor = function(seriesIndex) {
    	var key = "Series" + (seriesIndex+1);
    	var label = "Series " + (seriesIndex +1);
        if (tabAlreadyOpen(key)) {
          switchTab(key);
        } else {
          addTab(key, label, "<%=request.getContextPath()%>/seriesEditor.html?seriesIndex=" + seriesIndex);
        }
      };
      
      var openMissionEditor = function(seriesIndex, missionIndex) {
    	var key = "S" + (seriesIndex+1) + "M" + (missionIndex+1);
    	var label = "S" + (seriesIndex+1) + " M" + (missionIndex+1);
    	if (tabAlreadyOpen(key)) {
    	  switchTab(key);
    	} else {
          addTab(key, label, "<%=request.getContextPath()%>/missionEditor.html?seriesIndex=" + seriesIndex + "&missionIndex=" + missionIndex);
    	}
      };
      
      var openCutsceneEditor = function(seriesIndex, missionIndex, cutsceneIndex) {
    	var key = "S" + (seriesIndex+1) + "M" + (missionIndex+1) + "C" + cutsceneIndex;
    	var label = "S" + (seriesIndex+1) + " M" + (missionIndex+1) + " C" + cutsceneIndex;
    	if (tabAlreadyOpen(key)) {
    	  switchTab(key);
    	} else {
          addTab(key, label, "<%=request.getContextPath()%>/cutsceneEditor.html?seriesIndex=" + seriesIndex + "&missionIndex=" + missionIndex + "&cutsceneIndex=" + cutsceneIndex);
    	}
      };
    </script>
  </head>
  
  <body>
    <div class="ui-layout-north">WC Workshop</div>
    <div class="ui-layout-center">
      <div id="editorTabs">
        <ul>
          <!-- li class="editorTab" id="tab_savegameEditor"><a href="#tab-savegameEditor">Savegame Editor</a></li -->
        </ul>
        <!-- div id="tab-savegameEditor">
        </div -->
      </div>
    </div>
    <div class="ui-layout-west">
      <div id="projectViewTabs">
        <ul>
          <!-- li><a href="#tab-allContents">All</a></li -->
          <li><a href="#tab-campaignContents">Campaign</a></li>
        </ul>
        <!-- div id="tab-allContents"></div -->
        <div id="tab-campaignContents"></div>
      </div>
    </div>
  </body>
</html>