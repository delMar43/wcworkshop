<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
  var submitForm = function(e) {
    var form = $("#editProjectForm");
    var postData = form.serializeArray();
    var formURL = form.attr("action");
    $.ajax({
      url : formURL,
      type : "POST",
      data : postData,
      success : function(data, textStatus, jqXHR) {
        alert('ok');
      },
      error : function(jqXHR, textStatus, errorThrown) {
        alert('error: ' + errorThrown);
      }
    });
    e.preventDefault(); //STOP default action
  };
  
  $(function() {
    $("#editProjectForm").submit(submitForm(e));
  }
</script>

<form:form id="editProjectForm" action="editProject.html" method="POST">
  <table>
    <tr>
      <th>Title</th>
      <td><form:input path="title" />
    </tr>
    <tr>
      <th>Website</th>
      <td><form:input path="website" /></td>
    </tr>
  </table>
  <button id="submitButton">Create Project</button>
</form:form>
