<!DOCTYPE html>
<html>
<head>
  <title>Welcome at the World Creator's Workshop</title>
  <meta name="description" content="Wing Commander Campaign Editor" />
  <meta name="keywords" content="Wing Commander, Secret Missions, WC, SM, SM1, SM2, WC2, SO1, SO2, WCA, Academy, Armada, Kilrathi, Tiger's Claw, Victory, Concordia, Caernavon, We Create Worlds, World Creator's Workshop" />
</head>

<body>
  <div>authenticated: ${isAuthenticated}</div>
  <div>remembered: ${isRemembered}</div>
  <form method="POST" action="login.html">
     Username: <input type="text" name="username"/> <br/>
     Password: <input type="password" name="password"/>
     <input type="checkbox" name="rememberMe" value="true"/>Remember Me?<br/>
     <button type="submit">Go</button>
  </form>
</body>
</html>