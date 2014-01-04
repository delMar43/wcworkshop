<div id="headLeft">
  <h1 class="heading">World Creator's Workshop</h1>
  <!-- button style="font-family: Orbitron; font-weight: 700" onclick="openSavegameEditor();">Open Savegame Editor</button -->
  <button style="font-family: Orbitron; font-weight: 700" onclick="openProjectEditDialog();">New Project</button>
  <button style="font-family: Orbitron; font-weight: 700" onclick="openDownloadTab();">Download</button>
  <button style="font-family: Orbitron; font-weight: 700" onclick="openCredits();">Credits</button>
</div>
<div id="headCenter">&nbsp;</div>
<div id="headRight">
  <shiro:authenticated>
    <shiro:principal/><br/>
    <a href="<%=request.getContextPath()%>/logout.html">logout</a>
  </shiro:authenticated>
  <shiro:guest>
    <a href="#">Login or Signup</a>
  </shiro:guest>
</div>