<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome at the World Creator's Workshop</title>
  <meta name="description" content="Wing Commander Campaign Editor" />
  <meta name="keywords" content="Wing Commander, Secret Missions, WC, SM, SM1, SM2, WC2, SO1, SO2, WCA, Academy, Armada, Kilrathi, Tiger's Claw, Victory, Concordia, Caernavon, We Create Worlds, World Creator's Workshop" />
</head>

<body>
  Please, sign up to be able to upload, modify, save and generate new campaigns.
  <form method="POST" action="signup.html">
     Username: <input type="text" name="username"/> <br/>
     Password: <input type="password" name="password"/><br/>
     Confirm: <input type="password" name="passwordConfirm" /><br/>
     Email: <input type="text" name="email" />
     <br/>
     <button type="submit">Go</button>
  </form>
</body>
</html>