import json
#from BeautifulSoup import BeautifulSoup as bs

files = ["005A","010A","045A","046A","050A","062A","066A","067A","072A","076A","085A","088A","096A","111A","113A","124A"]

for i in range(1,10):
  files.append("00%s" % i)

for i in range(10,100):
  files.append("0%s" % i)

for i in range(100,136):
  files.append("%s" % i)

def genHTML(json):
  o = '<!DOCTYPE html>\n'
  o += '<html>\n'
  o += '  <head>\n'
  o += '    <title>Puzzle %s</title>\n' % json['num']
  o += '    <meta charset="utf-8"/>\n'
  o += '    <link rel="stylesheet" type="text/css" href="styles.css"/>\n'
  o += '    <script type="text/javascript" src="script.js"></script>\n'
  o += '    <style id="style"></style>\n'
  o += '  </head>\n'
  o += '  <body>\n'
  if len(json['steps']) > 0:
    o += '    <h1>Progress and Hint Coins</h1>\n'
    o += '    <div id="body">\n'
    o += '      <ul>\n'
    hc = 0

    for i in json['steps']:
      if isinstance(i, str):
        o += '        <li>%s</li>\n' % i
      elif isinstance(i, dict):
        if 'mobile' in dict:
          o += '        <li class="mobile">%s</li>\n' % i['mobile']
        elif 'img' in dict:
          o += '      </ul>\n'
          o += '      <img src="images/%s" onclick="bigMe(this)"/>\n' % i['img']['src']
          o += '      <p>%s</p>\n' % i['img']['desc']
          o += '      <ul>\n'
      else: # If Array of hint coins
        o += '        <li>Hint Coins<ul class="check">\n'
        for k in i:
          o += '          <li><input type="checkbox" id="hc%s"/><label for="hc%s">%s</label></li>\n' % (hc, hc, k)
          hc += 1
        o += '        </ul></li>\n'
    o += '      </ul>\n'
    o += '    </div>\n'
  o += '    <h1'

  try: # Regional Headers
    o += ' class="us">%s - %s</h1><h1 class="eu"' % (json['num'], json['a-title'])
  except:
    pass
  finally:
    o += '>%s - %s</h1>\n' % (json['num'], json['title'])

  # Tab Controls
  o += '    <div class="tab">\n'
  # Button 1
  o += '      <button class="tablinks" onclick="openTab(event, \'hint1\')">Hint 1</button>\n'
  # Button 2
  o += '      <button class="tablinks" onclick="openTab(event, \'hint2\')">Hint 2</button>\n'
  # Button 3
  o += '      <button class="tablinks" onclick="openTab(event, \'hint3\')">Hint 3</button>\n'
  # Super Hint
  #o += '      <button class="tablinks" onclick="openTab(event, \'hint4\')">Super Hint</button>\n'
  # Solution
  o += '      <button class="tablinks" onclick="openTab(event, \'solution\')">Solution</button>\n'
  o += '    </div>\n'

  # Hint Tab 1
  o += '    <div id="hint1" class="tabcontent">\n      <h1>Hint 1</h1>\n      <img src="images/%sH1.gif" onclick="bigMe(this)">\n    </div>\n' % json['num']
  # Hint Tab 2
  o += '    <div id="hint2" class="tabcontent">\n      <h1>Hint 2</h1>\n      <img src="images/%sH2.gif" onclick="bigMe(this)">\n    </div>\n' % json['num']
  # Hint Tab 3
  o += '    <div id="hint3" class="tabcontent">\n      <h1>Hint 3</h1>\n      <img src="images/%sH3.gif" onclick="bigMe(this)">\n    </div>\n' % json['num']
  # Hint Tab 4
  #o += '    <div id="hint4" class="tabcontent">\n      <h1>Hint 3</h1>\n      <img src="images/%sH3.gif" onclick="bigMe(this)">\n    </div>\n' % json['num']
  # Solution Tab
  o += '    <div id="solution" class="tabcontent">\n      <h1>Solution</h1>\n'

  for i in json['solution']: # Loop through solution entries
    if isinstance(i, str): # Custom Solutions
      o += i
    else:
      o += '      <section>\n        <img src="images/%s" onclick="bigMe(this)"/>\n        <p>%s</p>\n      </section>\n' % (i['img'], i['txt'])

  o += '    </div>\n  </body>\n  <footer>\n'

  if 'prev' in json:
    if isinstance(json['prev'], list):
      o += '    <p class="prev eu">Prev: <u onclick="changePage(\'%s\')">%s</u></p>\n' % (json['prev'][0]['num'], json['prev'][0]['name'])
      o += '    <p class="prev us">Prev: <u onclick="changePage(\'%s\')">%s</u></p>\n' % (json['prev'][1]['num'], json['prev'][1]['name'])
    else:
      o += '    <p class="prev">Prev: <u onclick="changePage(\'%s\')">%s</u></p>\n' % (json['prev']['num'], json['prev']['name'])

  if 'next' in json:
    if isinstance(json['next'], list):
      o += '    <p class="next eu">Next: <u onclick="changePage(\'%s\')">%s</u></p>\n' % (json['next'][0]['num'], json['next'][0]['name'])
      o += '    <p class="next us">Next: <u onclick="changePage(\'%s\')">%s</u></p>\n' % (json['next'][1]['num'], json['next'][1]['name'])
    else:
      o += '    <p class="next">Next: <u onclick="changePage(\'%s\')">%s</u></p>\n' % (json['next']['num'], json['next']['name'])

  o += '  </footer>\n  <div id="img" onclick="hideMe(this)">\n    <img src="">\n  </div>\n</html>'

  return o

for u in files:
  print('%s START' % u)
  with open('CV/JSON/%s.json' % u) as f:
    s = genHTML(json.loads(f.read()))
    with open('CV/HTML/%s.html' % u, 'w') as fi:
      fi.write(s)
  print('%s DONE' % u)
