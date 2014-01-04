<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
  var submitProjectEditForm = function() {
    $.ajax({
      type: "POST",
      url: $("#editProjectForm").attr("action"),
      data: $("#editProjectForm").serialize(),
      success: function(response) {
        addProjectNode(response);
      }
    });
  }
</script>

<form:form id="editProjectForm" action="editProject.json" method="POST">
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

<script type="text/javascript">
  $("#projectDescriptionTabs").tabs();
</script>