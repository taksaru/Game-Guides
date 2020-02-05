import json, os

with open('JSON/01 - P01.json') as f:
  j = json.loads(f.read())

print(j)

def htmlConvert(j):
  o = '<!DOCTYPE html><html><head><title>'

  if j['type'] == 'P':
    o += 'Puzzle ' + j['num'] + ' - '
  o += j['title']

  o += '</title><meta charset="utf-8"/><link rel="stylesheet" type="text/css" href="styles.css"/>'
  o += '<script type="text/javascript" src="script.js"></script></head>'
  o += '<body><h1>Progress and Hint Coins</h1><div id="body">'
  o += '<ul>'
  h = 0 # Hint coin counter
  for s in j['steps']:
    if isinstance(s,str):
      o += '<li>%s</li>' % s
    elif isinstance(s,list):
      o += '<li>Hint Coins<ul>'
      for c in s:
        o += '<li><input type="checkbox" id="hc%s" type="checkbox"/><label for="%s">%s</label></li>' %(h,h,c)
        h += 1
      o += '</ul></li>'
    else:
      o += '<li><details><summary>%s</summary><ul>' % (s['section'])
      for x in s['steps']:
        o += '<li>%s</li>' % x
      o += '</ul></details></li>'
  o += '</ul>'
  if j['type'] == 'P':

  o += '</body><footer><a id="prev" href="%s">%s</a>' % (j['prev']['link'],j['prev']['txt'])
  o += '<a id="next" href="%s">%s</a></footer>' % (j['next']['link'],j['next']['txt'])
  o += '<div id="img" onclick="hideMe(this)"><img src=""></div></html>'
