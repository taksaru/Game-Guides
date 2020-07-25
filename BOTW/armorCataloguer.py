import json
import os
j = {}

parts = ['Helm', 'Chest', 'Legs']

for file in os.listdir('armor'):
  b = False
  part = ''
  for p in parts:
    if p in file:
      part = p
      b = True
  if b:
    with open('armor/%s' % file, 'r') as f:
      data = json.load(f)
    b = False
    if part not in data['name'] and part in file:
      b = True
      data['name'] = '%s %s' % (data['name'], part)
    if b:
      print('UPDATING %s' % file)
      with open('armor/%s' % file, 'w') as f:
        json.dump(data, f, indent=2)

while True:
  j = {}
  j['name'] = input('Armor Name: ')
  set = True if input('Set (Y/N): ') == 'Y' else False
  j['level'] = 0
  j['list'] = []
  tmp = {}
  for i in range(1,5):
    tmp = {}
    print('---- LEVEL %s ----' % i)
    tmp['material1'] = input('Material 1: ').strip()
    try:
      tmp['quantity1'] = int(input('Quantity 1: '))
    except:
      tmp['quantity1'] = 0
    tmp['material2'] = input('Material 2: ').strip()
    try:
      tmp['quantity2'] = int(input('Quantity 2: '))
    except:
      tmp['quantity2'] = 0
    j['list'].append(tmp)
  if(set):
    for i in parts:
      with open('armor/%s%s.json' % (j['name'].replace(" ", ""), i), 'w') as f:
        json.dump(j, f, indent=2)
    print('---- %s ARMOR SET COMPLETE ----' % j['name'])
  else:
    with open('armor/%s.json' % j['name'].replace(" ", ""), 'w') as f:
      json.dump(j, f, indent=2)
    print('---- %s ARMOR COMPLETE ----' % j['name'])
