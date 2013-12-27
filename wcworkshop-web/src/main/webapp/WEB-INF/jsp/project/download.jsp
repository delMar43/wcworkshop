<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h4>Notes</h4>
<p>
  Generate downloadable files by clicking "Generate".
</p>
<c:forEach items="${downloadModels}" var="downloadModel">
  <fieldset>
    <legend>${downloadModel.projectTitle}</legend>
    <table>
      <thead>
        <tr>
          <th>Filename</th>
          <th>Change Date</th>
        </tr>
      </thead>
    <c:forEach items="${downloadModel.files}" var="downloadFile">
      <tr>
        <td>
          <a href="<%=request.getContextPath()%>/generated/${downloadModel.projectId}/${downloadFile.filename}.bin">${downloadFile.filename}</a>
        </td>
        <td>
          ${downloadFile.changeDate}
        </td>
      </tr>
    </c:forEach>
    </table>
  </fieldset>
</c:forEach>