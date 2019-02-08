from os import listdir

s = ''

for i in listdir():
  if '-' in i:
    with open(i, 'r') as f:
      s += f.read()
for i in range(1,10):
  if s.count('Korok 00%s: ' % i) != 1:
    print(i)
for i in range(10,100):
  if s.count('Korok 0%s: ' % i) != 1:
    print(i)
for i in range(101,901):
  if s.count('Korok %s:' % i) != 1:
    print(i)
