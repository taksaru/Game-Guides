var state = [];

function setState(stateArray){
  state = stateArray;

  focusSet = false;

  state.forEach((item,index) => {
    e = document.getElementById('c' + index);

    e.checked = !!item;

    if(!item && !focusSet && index !== 0){
      e.scrollIntoView()
      focusSet = true;
    }
  });
}

function checkClick(check){
  state[parseInt(check.id.substr(1))] = check.checked ? 1 : 0;
}

function getState(){
  return state.join(',');
}

function fullImg(img){
  d = document.getElementById("modBox");
  i = document.getElementById("modImg");
  i.src = img.src;
  d.style.display = 'block';
}

function closeMod(){
  d = document.getElementById("modBox");
  d.style.display = 'none';
}

function showToast(){
  Android.showToast("TESTING");
}
