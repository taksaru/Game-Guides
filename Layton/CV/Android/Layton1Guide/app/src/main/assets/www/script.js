function openTab(evt, name){
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName('tabcontent');
  for(i = 0; i < tabcontent.length; i++){
    tabcontent[i].style.display = "none";
  }

  tablinks = document.getElementsByClassName('tablinks');
  for(i = 0; i < tabcontent.length; i++){
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(name).style.display = "block";
  evt.currentTarget.className += " active";
}

function setState(yank, mobile){
  s = document.getElementById("style");
  for(var i = 0; i < s.sheet.rules.length; i++){
    s.sheet.removeRule(s.sheet.rules[i]);
  }

  if(yank){
    s.sheet.insertRule('.eu{display: none}');
    s.sheet.insertRule('.us{display: block}');
  }else{
    s.sheet.insertRule('.eu{display: block}');
    s.sheet.insertRule('.us{display: none}');
  }

  if(mobile){
    s.sheet.insertRule('.mobile{display: block}');
  }else{
    s.sheet.insertRule('.mobile{display: none}');
  }
}

function changePage(num){
  Android.changePage(num);
}

function hideMe(i){
  i.style.display = 'none';
}

function bigMe(i){
  var x = document.getElementById('img');
  x.firstElementChild.setAttribute('src', i.getAttribute('src'));

  x.style.display = 'block';
}