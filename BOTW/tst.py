from os import listdir
import re

s = ''

for i in listdir():
  ##print('-' in i and '.md' in i, ': ', i)
  if '-' in i and '.md' in i:
    with open(i, 'r') as f:
      s += f.read()

def korokCheck():
  s = ''

  for i in listdir():
    ##print('-' in i and '.md' in i, ': ', i)
    if '-' in i and '.md' in i:
      with open(i, 'r') as f:
        s += f.read()

  k = 0

  badK = 0
  for i in range(1,10):
    j = s.count('Korok 00%s: ' % i)
    if j != 1:
      print(i, ': ', j)
      k += j
      badK += 1
    else:
      k += 1
    ##print(k,'/',i)
  for i in range(10,100):
    j = s.count('Korok 0%s: ' % i)
    if j != 1:
      print(i, ': ', j)
      k += j
      badK += 1
    else:
      k += 1
    ##print(k,'/',i)
  for i in range(100,901):
    j = s.count('Korok %s:' % i)
    if j != 1:
      print(i, ': ', j)
      k += j
      badK += 1
    else:
      k += 1
  ##print(k,'/',i)
  print('Koroks: ', k)
  return k, badK

def genList(counter):
  list = []
  counter += 1
  if(counter > 10):
    for i in range(1, 10):
      list.append("00%s" % i)
  else:
    for i in range(0, counter):
      list.append("00%s" % i)
  if(counter > 100):
    for i in range(10, 100):
      list.append("0%s" % i)
  else:
    for i in range(0, counter):
      list.append("0%s" % i)
  for i in range(100, counter):
    list.append(str(i))
  return list


k = 0
badK = 0

k, badK = korokCheck()


korokRegex = r"Korok [0-9][0-9][0-9]:"
kCounter = genList(900)

while(k == 900 and badK > 0):
  counter = 0
  for i in listdir():
    ##print('-' in i and '.md' in i, ': ', i)
    # Get File
    if '-' in i and '.md' in i:
      # File Content
      fi = ""
      # open file
      with open(i, 'r') as f:
        # Loop through lines
        for line in f.readlines():
          # If match
          if re.search(korokRegex, line): # Replace
            print('REGEX: %s' % line)
            fi += "%s" % re.sub(korokRegex, 'Korok %s:' % kCounter[counter], line)
            counter += 1
          else:
            fi += line
      # Replace file
      with open(i, 'w') as f:
        f.write(fi)
  k, badK = korokCheck()

c = 0

for i in range(1,121):
  j = s.count('(%s/120)'% i)
  if j != 1:
    print('Shrine %s: %s' %(i,j))
    c += j
  else:
    c += 1

print('Shrines: %s' % c)

imgs = ['Akkala', 'Castle', 'DP', 'Eldin', 'Faron', 'Gerudo', 'Hateno', 'Hebra', 'Hylia', 'Hyrule', 'Lanaryu', 'Plateau', 'Tabantha', 'Wasteland', 'Woodland']

for i in imgs:
  print('%s: %s'% (i, s.count('images/%s' % i)))
