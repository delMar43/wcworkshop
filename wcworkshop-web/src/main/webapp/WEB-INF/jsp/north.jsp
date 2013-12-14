<div id="headLeft">
  <h1 class="heading">WC Workshop</h1>
  <button style="font-family: Orbitron; font-weight: 700" onclick="openSavegameEditor();">Open Savegame Editor</button>
</div>
<div id="headCenter">&nbsp;</div>
<div id="headRight">
  <shiro:authenticated>
    <shiro:principal/><br/>
    <a href="<%=request.getContextPath()%>/logout.jsp">logout</a>
  </shiro:authenticated>
  <shiro:guest>
    <a href="#">Login or Signup</a>
  </shiro:guest>
</div>