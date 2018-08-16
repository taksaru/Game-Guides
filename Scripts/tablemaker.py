from json import loads, dumps
from os import system

def getHeaders():
  h = []
  x = raw_input('Table Column: ')
  while x != '':
    y = raw_input('Format: ')
    h.append({'name':x,'format':y})
    x = raw_input('Table Column: ')
  return h

def retrieveDB(db_f,db_s):
  j = loads(open(db_f,'r').read())
  db = j[db_s]
  main = db['main']
  return j,db,main

db_f = raw_input('Database File: ')
db_s = raw_input('Database Name: ')

j = {}

try:
  j = loads(open(db_f,'r').read())
except:
  with open(db_f,'w') as f:
    f.write(dumps(j))

db = {}

try:
  db = j[db_s]
except:
  j[db_s] = {}

try:
  heads = db['heads']
except:
  heads = getHeaders()
  db['heads'] = heads

try:
  main = db['main']
except:
  db['main'] = {}
  main = {}

header = '<tr>'
for i in heads:
  header += '<th>%s</th>' % i['name']
header += '</tr>'

x = raw_input('Table Title: ')
while x != '':
  t = '<table><tr><th colspan="%s">%s</th></tr>%s' % (len(heads),x,header)
  y = raw_input('All Rows? ')
  while y == '':
    r = '<tr>'
    for i in heads:
      x = raw_input('%s(%s): ' % (i['name'],i['format']))
      if i == heads[0]:
        s = x
        if x in main:
          r = main[x]
          break
        if x[:4] == 'bar-':
          r = '<tr><th colspan="%s">%s</th>' % (len(heads),x[4:])
          break
      if i['format'] != '':
        x = i['format'] % x
      r += '<td>%s</td>' % x
    if x[:4] != 'bar-':
      main[s] = r
    r += '</tr>'
    t += r
    y = raw_input('All Rows? ')
  t += '</table>'
  db['main'] = main
  j[db_s] = db
  with open(db_f,'w') as f:
    f.write(dumps(j))
  j,db,main = retrieveDB(db_f,db_s)
  system("echo '%s' | pbcopy" % t)
  x = raw_input('Table Title ')
