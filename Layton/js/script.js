function openTab(evt, name){
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName('tabcontent');
  for(i = 0; i < tabcontent.length; i++){
    tabcontent[i].style.display = "none";
    console.log(tabcontent[i]);
  }
  tablinks = document.getElementsByClassName('tablinks');
  for(i = 0; i < tabcontent.length; i++){
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(name).style.display = "block";
  evt.currentTarget.className += " active";
}

document.addEventListener("backbutton", function(e){
  e.preventDefault();
  if confirm('Close Application?'){
    console.log('CLOSE');
  }
})
