<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<table>
  <tbody>
    <tr>
      <th>Foreground</th>
      <td>${cutsceneUtil.getForeground(cutscene.foreground)}</td>
    </tr>
    <tr>
      <th>Background</th>
      <td>${cutsceneUtil.getBackground(cutscene.background)}</td>
    </tr>
    <tr>
      <th>Text Color</th>
      <td>${cutsceneUtil.getTextColor(cutscene.textColor)}</td>
    </tr>
  </tbody>
</table>