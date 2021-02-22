function openAddition() {
    document.getElementById("addition").style.width = "100%";
}
  
function closeAddition() {
    document.getElementById("addition").style.width = "0";
}
$('#addTODO').click(openAddition);
$('#add-back').click(closeAddition);