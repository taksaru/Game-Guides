Handlebars.registerHelper('steps', function(items, options){
  var out = "<ul>";
  var x;
  var hc = 0
  for(var i = 0; i < items.length; i++){
    x = items[i];
    if(typeof(x) === "string"){
      out += "<li>" + x + "</li>";
    }else{
      out += "<li>Hint Coins<ul>";
      for(var j = 0; j < x.length; j++){
        // <li><input type="checkbox" id="hc1" type="checkbox"/><label for="hc1">Light</label></li>
        out += "<li><input type=\"checkbox\" id=\"hc" + hc + "\" type=\"checkbox\"/><label for=\"hc" + hc + "\">" + x[j] + "</label></li>";
        hc++;
      }
      out += "</ul></li>";
    }
  }
  return out + "</ul>"
});

Handlebars.registerHelper('sol', function(items, options){
  var out = ""

  for(var i = 0; i < items.length; i++){
    out += "<img src=\"images/" + items[i].img + "\"><p>" + items[i].txt + "</p>"
  }

  return out;
});

Handlebars.registerHelper('trial', function(items, options){
  var out = "<ul>";
  var x;
  var hc = 0
  for(var i = 0; i < items.length; i++){
    x = items[i];
    if(typeof(x) === "string"){
      out += "<li>" + x + "</li>";
    }else{
      if(Array.isArray(x)){
        out += "<li>Hint Coins<ul>";
        for(var j = 0; j < x.length; j++){
          // <li><input type="checkbox" id="hc1" type="checkbox"/><label for="hc1">Light</label></li>
          out += "<li><input type=\"checkbox\" id=\"hc" + hc + "\" type=\"checkbox\"/><label for=\"hc" + hc + "\">" + x[j] + "</label></li>";
          hc++;
        }
      }else{
        out += "<li><details><summary>" + x['section'] + '</summary><ul>'
        for(var j = 0; j < x['steps'].length; j++){
          out += '<li>' + x['steps'][j] + '</li>'
        }
        out += '</ul></details></li>'
      }
      out += "</ul></li>";
    }
  }
  return out + "</ul>"
});


Handlebars.registerHelper('sbar', function(items, options){
  var out = '';
  var x;
  for(var i = 0; i < items.length; i++){
    x = items[i];
    out += '<a href="' + x.id + '"><li>';
    if(x.type == "P"){
      out += x.num + ' - ';
    }
    out += x.title + '</li></a>';
  }
  return out;
});
