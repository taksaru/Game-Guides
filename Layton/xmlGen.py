import json
#from BeautifulSoup import BeautifulSoup as bs

files = ["005A","010A","045A","046A","050A","062A","066A","067A","072A","076A","085A","088A","096A","111A","113A","124A"]

for i in range(1,10):
  files.append("00%s" % i)

for i in range(10,100):
  files.append("0%s" % i)

for i in range(100,136):
  files.append("%s" % i)

x = ''

for f in files:
  with open('CV/JSON/%s.json' % f) as file:
    j = json.loads(file.read())
    x += '<string name="puzzle%s">%s - %s</string>\n' % (j['num'].lower(), j['num'][0:3], j['title'])
    if 'a-title' in j:
      x += '<string name="puzzle%sa">%s - %s</string>\n' % (j['num'].lower(), j['num'][0:3], j['a-title'])

x = x.replace("'", "\\'")

with open('strings.xml', 'w') as f:
  f.write(x)
