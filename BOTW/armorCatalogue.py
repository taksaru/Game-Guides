import json
import os
import operator

dict = {}

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

  if data['level'] < 4:
    updateDict(data['list'][3])
  if data['level'] < 3:
    updateDict(data['list'][2])
  if data['level'] < 2:
    updateDict(data['list'][1])
  if data['level'] < 1:
    updateDict(data['list'][0])

del dict['']

for item in sorted(dict.keys()):
  #if 'Farosh' in item:
    print('%s: %s' % (item, dict[item]))
