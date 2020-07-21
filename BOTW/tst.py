from os import listdir

s = ''

for i in listdir():
  ##print('-' in i and '.md' in i, ': ', i)
  if '-' in i and '.md' in i:
    with open(i, 'r') as f:
      s += f.read()


k = 0

for i in range(1,10):
  j = s.count('Korok 00%s: ' % i)
  if j != 1:
    k += j
  else:
    k += 1
  ##print(k,'/',i)
for i in range(10,100):
  j = s.count('Korok 0%s: ' % i)
  if j != 1:
    print(i, ': ', j)
    k += j
  else:
    k += 1
  ##print(k,'/',i)
for i in range(100,901):
  j = s.count('Korok %s:' % i)
  if j != 1:
    print(i, ': ', j)
    k += j
  else:
    k += 1
  ##print(k,'/',i)

print('Koroks: ', k)

c = 0

for i in range(1,121):
  j = s.count('(%s/120)'% i)
  if j != 1:
    print('Shrine %s: %s' %(i,j))
    c += j
  else:
    c += 1

print('Shrines: %s' % c)
