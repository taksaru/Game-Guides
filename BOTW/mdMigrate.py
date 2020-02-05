try:
  with open('pop4.sql', 'r') as f:
    s = f.read()
    i = -1
    lines = s.splitlines()
    # INSERT INTO entries(subsection, order, title, description, type, ref) VALUES();
    l = lines[-1]
    vals = l[77:-2]
    vs = vals.split(',')
    subSection = int(vs[0])
    order = int(vs[1]) + 1
    while(int(lines[i].split(',')[-2]) != 1):
      i -= 1
    korok = int(lines[i].split(',')[-1][:-2]) # Korok Counter
except:
  subSection = 1
  order = 1
  korok = 0

while True:
  str = input('New SubSection(Y/N): ')
  if(str == 'Y'):
    subSection += 1
    order = 1
  print('-------------------------------- Section: %s - Order: %s -----------------------------------' % (subSection, order))
  print('Koroks: %s' % korok)
  print('------------------------------------------ TYPES ------------------------------------------')
  print('String: 0\tKorok: 1\tSide Quest: 2\tMain Quest: 3\tShrine Quest: 4\nShrine: 5\tTower: 6\tTalus: 7\tHinox: 8\tMolduga: 9\tMemory: 10')
  i = int(input('Entry Type: '))
  ref = 'NULL'
  desc = input('Description: ')
  desc = desc.replace("'", "''")
  if(i == 0):
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, NULL, '%s', %s, NULL);\n" % (subSection, order, desc, i)
  if(i == 1):
    korok += 1
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, NULL, '%s', %s, %s);\n" % (subSection, order, desc, i, korok)
  elif(i == 2):
    title = input('Side Quest Title: ')
    title = title.replace("'", "''")
    ref = int(input('Side Quest ID: '))
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, '%s', '%s', %s, %s);\n" % (subSection, order, title, desc, i, ref)
  elif(i == 3):
    title = input('Main Quest Title: ')
    title = title.replace("'", "''")
    ref = int(input('Main Quest ID: '))
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, '%s', '%s', %s, %s);\n" % (subSection, order, title, desc, i, ref)
  elif(i == 4):
    title = input('Shrine Quest Title: ')
    title = title.replace("'", "''")
    ref = int(input('Shrine Quest ID: '))
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, '%s', '%s', %s, %s);\n" % (subSection, order, title, desc, i, ref)
  elif(i == 5):
    title = input('Shrine Title: ')
    title = title.replace("'", "''")
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, '%s', '%s', %s, NULL);\n" % (subSection, order, title, desc, i)
  elif(i == 6): # Tower
    title = input('Tower Title: ')
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, '%s', '%s', %s, NULL);\n" % (subSection, order, title, desc, i)
  elif(i == 7): # Talus
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, NULL, '%s', %s, NULL);\n" % (subSection, order, desc, i)
  elif(i == 8): # Hinox
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, NULL, '%s', %s, NULL);\n" % (subSection, order, desc, i)
  elif(i == 9): # Molduga
    out = "INSERT INTO entries(subsection, order, title, description, type, ref) VALUES(%s, %s, NULL, '%s', %s, NULL);\n" % (subSection, order, desc, i)
  elif(i == 10): # Memory
    ref = int(input('Memory Number: '))
    title = input('Memory Title: ')
    title = title.replace("'","''")
    out = "INSERT INTO entries(subSection, order, title, description, type, ref) VALUES(%s, %s, '%s', '%s', %s, %s);\n" % (subSection, order, title, desc, i, ref)
  order += 1
  with open('pop4.sql', 'a') as f:
    f.write(out)
  out = ''
