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
