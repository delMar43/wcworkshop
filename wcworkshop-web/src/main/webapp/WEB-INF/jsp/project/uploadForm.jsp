<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="<%=request.getContextPath() %>/styles/jquery.plupload.queue.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery.plupload.queue.min.js">
<script type="text/javascript">
var submitProjectUploadForm = function() {
  $.ajax({
    type: "POST",
    url: $("#projectUploadForm").attr("action"),
    data: $("#projectUploadForm").serialize(),
    success: function(response) {
      var respObj = $.parseJSON(response);
      loadProject(respObj.projectId);
    }
  });
}
</script>
<div id="html5_uploader"></div>

<script type="text/javascript">
$("#html5_uploader").pluploadQueue({
    // General settings
    runtimes : 'html5',
    url : '<%=request.getContextPath()%>/uploadProject.html',
    max_file_size : '1mb',
    chunk_size : '1mb',
    unique_names : true,

    // Specify what files to browse for
    filters : [
        {title : "Data files", extensions : "000,001,002"},
    ]
});


</script>