import json
import os
import operator

dict = {}

d2 = {}

def updateDict(entry):
  try:
    dict[entry['material1']] += entry['quantity1']
  except:
    dict[entry['material1']] = entry['quantity1']
  try:
    dict[entry['material2']] += entry['quantity2']
  except:
    dict[entry['material2']] = entry['quantity2']

for file in os.listdir('armor'):
  with open('armor/%s' % file, 'r') as f:
    data = json.load(f)

  l = data['level']

  if l < 4:
    updateDict(data['list'][3])
  if l < 3:
    updateDict(data['list'][2])
  if l < 2:
    updateDict(data['list'][1])
  if l < 1:
    updateDict(data['list'][0])
'''
  if l < 4:
    print('--- %s ---' % data['name'])
    if l < 1:
      print('-- Level 1 --')
      req = data['list'][0]
      print('%s: %s' % (req['material1'], req['quantity1']))
      if req['material2']:
        print('%s: %s' % (req['material2'], req['quantity2']))
    if l < 2:
      print('-- Level 2 --')
      req = data['list'][1]
      print('%s: %s' % (req['material1'], req['quantity1']))
      if req['material2']:
        print('%s: %s' % (req['material2'], req['quantity2']))
    if l < 3:
      print('-- Level 3 --')
      req = data['list'][2]
      print('%s: %s' % (req['material1'], req['quantity1']))
      if req['material2']:
        print('%s: %s' % (req['material2'], req['quantity2']))
    if l < 4:
      print('-- Level 4 --')
      req = data['list'][3]
      print('%s: %s' % (req['material1'], req['quantity1']))
      if req['material2']:
        print('%s: %s' % (req['material2'], req['quantity2']))
'''
try:
  del dict['']
except:
  pass

for item in sorted(dict.keys()):
  #if 'Farosh' in item:
    print('%s: %s' % (item, dict[item]))
