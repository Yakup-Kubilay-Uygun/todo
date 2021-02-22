function openAddition() {
    document.getElementById("addition").style.width = "100%";
}
  
function closeAddition() {
    document.getElementById("addition").style.width = "0";
}
/*
function openTabulation() {
    document.getElementById("tabulation").style.width = "100%";
}
  
function closeTabulation() {
    document.getElementById("tabulation").style.width = "0";
}
*/
$('#addUser').click(openAddition);
$('#add-back').click(closeAddition);
/*
$('#listUser').click(openTabulation);
$('#list-back').click(closeTabulation);
*/