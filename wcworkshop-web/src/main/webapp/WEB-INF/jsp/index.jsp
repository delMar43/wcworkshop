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
        $(function() {
            var layout = $("body").layout({ applyDefaultStyles: true });
            layout.sizePane("west", 280);
            $("#projectViewTabs").tabs();
            
            $.ajax({
              url: "<%=request.getContextPath()%>/campaignContentsTree.html",
              success: function(data, textStatus, jqXHR) {
                $("#tab-campaignContents").html(data);
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
              }
            });
        });
    </script>
  </head>
  
  <body>
    <div class="ui-layout-north">WC Workshop</div>
    <div class="ui-layout-center">
      
    </div>
    <div class="ui-layout-west">
      <div id="projectViewTabs">
        <ul>
          <li><a href="#tab-allContents">All</a></li>
          <li><a href="#tab-campaignContents">Campaign</a></li>
        </ul>
        <div id="tab-allContents"></div>
        <div id="tab-campaignContents"></div>
      </div>
    </div>
  </body>
</html>