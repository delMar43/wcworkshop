<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
  var submitForm = function() {
    $.ajax({
      type: "POST",
      url: $("#editProjectForm").attr("action"),
      data: $("#editProjectForm").serialize(),
      success: function() {alert('done')}
    });
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
    <tr>
      <th>Description</th>
      <td>
        <div id="projectDescriptionTabs">
          <ul>
            <li><a href="#englishDescription">English</a></li>
          </ul>
          <div id="englishDescription">
            <form:textarea path="descriptions['english']"/>
          </div>
        </div>
      </td>
    </tr>
  </table>
</form:form>
<button id="submitButton" onclick="submitForm()">Create Project</button>

<script type="text/javascript">
  $("#projectDescriptionTabs").tabs();
</script>