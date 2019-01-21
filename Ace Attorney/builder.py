import json

def crossExam():
  c = {'steps': []}
  c['section'] = input('Section: ')
  s = input('Next Step: ')
  while s != "":
    c['steps'].append(s)
    s = input('Next Step: ')
  return c

def psycheLock():
  p = {"locks": int(input('Psyche Locks: ')), 'steps': []}
  s = input('Next Step: ')
  while s != "":
    p['steps'].append(s)
    s = input('Next Step: ')
  return p

n = int(input("Starting ID: "))

while True:
  x = {'id': n}
  t = input("Type: ")
  while t != "T" and t != "I":
    print("INVALID TYPE")
    t = input("Type: ")
  x['type'] = t
  x['title'] = input('Title: ')
  l = []
  y = input('Next Step: ')
  while y != '':
    if y == 'ce' and t == 'T':
      l.append(crossExam())
    if y == 'pl' and t == 'I':
      l.append(psycheLock())
  p = input('Previous Text: ')
  x['prev'] = {'link': n - 1, 'txt': p}
  p = input('Next Text: ')
  x['next'] = {'link': n + 1, 'txt': p}

  f = input('File: ')

  with open('JSON/%s' % f, 'w') as o:
    o.write(json.dumps(x))
  n += 1
