import os

for i in os.listdir('PB/images/'):
  if(i.startswith('PL2')):
    print('RENAMING %s TO %s' % (i, i[3:]))
    os.rename('PB/images/%s' % i, 'PB/images/%s' % i[3:])
