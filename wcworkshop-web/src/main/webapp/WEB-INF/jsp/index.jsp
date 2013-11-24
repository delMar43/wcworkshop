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
      var openSeries = '';
      var openMissions = '';
      
      $(function() {
        var layout = $("body").layout({ applyDefaultStyles: true });
        layout.sizePane("west", 280);
        $("#projectViewTabs").tabs();
        editorTabs = $("#editorTabs").tabs();
        
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
        
        $.ajax({
          url: "<%=request.getContextPath()%>/savegameEditor.html",
          success: function(data, textStatus, jqXHR) {
            $("#tab-savegameEditor").html(data);
          }
        });
      });

      var addTab = function(label, href) {
        var ul = editorTabs.find(".ui-tabs-nav");
        $("<li><a href='" + href + "'>" + label+ "</a> <span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>").appendTo(ul);
        editorTabs.tabs("refresh");
      }
      
      var openSeriesEditor = function(seriesIndex) {
        $.ajax({
          url: "<%=request.getContextPath()%>/seriesEditor.html",
          data: "seriesIndex=" + seriesIndex,
          success: function(data, textStatus, jqXHR) {
            addTab("Series " + (seriesIndex+1), "seriesEditor.html?seriesIndex=" + seriesIndex);
          }
        });
      };
      
      var openMissionEditor = function(seriesIndex, missionIndex) {
        $.ajax({
          url: "<%=request.getContextPath()%>/missionEditor.html",
          data: "seriesIndex=" + seriesIndex + "&missionIndex=" + missionIndex,
          success: function(data, textStatus, jqXHR) {
            addTab("S" + (seriesIndex+1) + "M" + (missionIndex+1), "missionEditor.html?seriesIndex=" + seriesIndex + "&missionIndex=" + missionIndex);
          }
        });
      };
    </script>
  </head>
  
  <body>
    <div class="ui-layout-north">WC Workshop</div>
    <div class="ui-layout-center">
      <div id="editorTabs">
        <ul>
          <li><a href="#tab-savegameEditor">Savegame Editor</a></li>
        </ul>
        <div id="tab-savegameEditor">
        </div>
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