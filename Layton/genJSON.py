import os
import json

template = {
  'num': '000',
  'title': '',
  'steps': [''],
  'solution': [
    {
      'img': '000S.png',
      'txt': ''
    }
  ],
  'prev': {
    'num': '000',
    'name': ''
  },
  'next': {
    'num': '000',
    'name': ''
  }
}

files = ['tst']

for i in range(1,10):
  files.append("00%s" % i)

for i in range(10,100):
  files.append("0%s" % i)

for i in range(100,186):
  files.append("%s" % i)

dirfiles = os.listdir('Katrielle/JSON/')

for f in files:
  if ('%s.json' % f) not in dirfiles:
    with open('Katrielle/JSON/%s.json' % f, 'w') as x:
      template['num'] = f
      template['solution'][0]['img'] = '%sS.gif' % f
      x.write(json.dumps(template, indent=2))
