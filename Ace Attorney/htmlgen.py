import json
'''
with open('main.json', 'w') as f:
  js = json.loads(f.read())
'''

js = [
  {
    "id": 1,
    "num": "01",
    "type": "P",
    "title": "Paths of Light",
    "steps": [
      [
        "Light",
        "Painting",
        "Gramaphone"
      ],
      "Check Bookshelf"
    ],
    "solution": [
      {
        "img": "p1s.gif",
        "txt": "Remove the green and blue paths"
      }
    ],
    "prev": {
      "link": "",
      "txt": ""
    },
    "next": {
      "link": "02",
      "txt": "02 - Barton's Burger"
    }
  }
]

for j in js:
  out = '<!DOCTYPE html>\n<html><head><title>'

  if j['type'] == 'P':
    out += 'Puzzle %s - %s' % (j['num'], j['title'])
  elif j['type'] == 'T':
    out += j['title']

  out += '</title><meta charset="utf-8"/><link rel="stylesheet" href="css/styles.css"><script type="text/javascript" src="js/script.js"></script></head><body>'

  out += 'Progress and Hint Coins'

  out += '</h1><ul>'

  if j['type'] == 'P': # Puzzle
    l = j['steps']
    # Foreach step
    for k in l:
      hc = 0 # Hint Coin counter
      out += '<li>'
      if isinstance(k,str): # Step
        out += k
      else: # Hint Coins
        out += 'Hint Coins<ul>'
        # Foreach hint coin
        for h in k:
          out += '<li><input type="checkbox" id="hc%s"/><label for="hc%s">%s</label></li>' %(hc, hc, h)
          hc += 1
        out += '</ul>'
      out += '</li>'
  else: # Trial
    l = j['steps']
    for k in l: # Foreach step
      hc = 0
      out += '<li>'
      if isinstance(k,str): # Step
        out += k
      elif k is list: # Hint Coins
        out += 'Hint Coins<ul>'
        # Foreach hint coin
        for h in k:
          out += '<li><input type="checkbox" id="hc%s"/><label for="hc%s">%s</label></li>' %(hc, hc, h)
          hc += 1
        out += '</ul>'
      else: # Cross Examination
        out += '<details><summary>%s</summary><ul>' % k['section']
        for s in k['steps']: # Steps of Cross Examination
          out += '<li>%s</li>' % s
        out += '</ul></details>'
      out += '</li>'
  out += '</ul>'
  if j['type'] == 'P':
    out += '<h1>%s - %s</h1><div class="tab"><button class="tablinks" onclick="openTab(event, \'hint1\')">Hint 1</button><button class="tablinks" onclick="openTab(event, \'hint2\')">Hint 2</button><button class="tablinks" onclick="openTab(event, \'hint3\')">Hint 3</button><button class="tablinks" onclick="openTab(event, \'hint4\')">Super Hint</button><button class="tablinks" onclick="openTab(event, \'solution\')">Solution</button></div>' % (j['num'], j['title'])

    out += '<div id="hint1" class="tabcontent"><h1>Hint 1</h1><img src="images/p%sh1.gif"/></div>' % j['num']
    out += '<div id="hint2" class="tabcontent"><h1>Hint 2</h1><img src="images/p%sh2.gif"/></div>' % j['num']
    out += '<div id="hint3" class="tabcontent"><h1>Hint 3</h1><img src="images/p%sh3.gif"/></div>' % j['num']
    out += '<div id="hint4" class="tabcontent"><h1>Super Hint</h1><img src="images/p%sh4.gif"/></div>' % j['num']
    out += '<div id="solution" class="tabcontent"><h1>Solution</h1>'

    for a in j['solution']:
      out += '<img src="images/%s"/><p>%s</p>' % (a['img'], a['txt'])
    out += '</div></body><footer>'

    if j['id'] != 1:
      out += '<a id="prev" href="%02d.html">%s</a>' % (j['id'] - 1, j['prev']['txt'])
    out += '<a href="search.html">Search</a>'

    if j['id'] != 94:
      out += '<a id="next" href="%02d.html">%s</a>' % (j['id'] + 1, j['next']['txt'])
    out += '</footer></html>'
  with open('%02d.html' % j['id'], 'w') as f:
    f.write(out)
