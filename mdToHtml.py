count = 0

def nextLine(lines):
  try:
    return lines.pop(0)
  except:
    return False

def inputCheck(text):
  global count
  if input('Input %s: ' % text):
    count += 1
    return '<input type="checkbox" id="c%s"/><label for="c%s">%s</label>' % (count, count, text)
  else:
    return text

def mdTohtml(mdURL, htmlURL):
  lines = open(mdURL).read().splitlines()

  line = nextLine(lines)
  output = '<body>'

  while line != False:
    if line.startswith('#'):
      i = 1
      for char in line[1:]:
        if(char == '#'):
          i += 1
        else:
          break
      output += '<h%s>%s</h%s>' % (i, line[i + 1:], i)
    elif line.startswith('!['):
      vals = line.split('](')
      output += '<img alt="%s" src="%s"/></br></br>' % (vals[0][2:], vals[1][:-1])
    elif line.startswith('* '):
      output += '<ul><li>%s</li>' % inputCheck(line[2:])
      line = nextLine(lines)
      depth = 0
      while line:
        if line.startswith(('  ' * depth) + '* '):
          output += '<li>%s</li>' % inputCheck(line[(depth + 1) * 2:])
        elif line.startswith(('  ' * (depth + 1)) + '* '):
          depth += 1
          output += '<ul><li>%s</li>' % inputCheck(line[2 * (depth + 1):])
        else:
          for i in range(0, depth):
            if line.startswith(('  ' * i) + '* '):
              output += '</ul>' * (depth - i)
              output += '<li>%s</li>' % inputCheck(line[2 * (i + 1):])
              depth = i
              break
          output += '</ul>' + ('</ul>' * depth)
        line = nextLine(lines)
    elif line != '':
      output += '<p>%s</p>' % line
    line = nextLine(lines)
  output += '</body>'

  with open(htmlURL, 'w') as f:
    f.write(output)
  print(output)

files = ['02 - DP1.md', '03 - Hateno1.md', '04 - DP2.md',
  '05 - Hateno2.md', '06 - Lanaryu1.md', '07 - Woodland1.md', '08 - Ridgeland1.md',
  '09 - Tabantha1.md', '10 - Hebra1.md', '11 - Eldin1.md', '12 - Akkala1.md',
  '13 - Central1.md', '14 - Wasteland1.md', '15 - Gerudo1.md', '16 - Wasteland2.md',
  '17 - Champion\s Ballad.md', '18 - Lanaryu2.md', '19 - Hateno3.md', '20 - Hylia1.md',
  '21 - Faron1.md', '22 - Central2.md', '23 - Hyrule Castle.md', '24 - PostGame.md']

for fileName in files:
  s = fileName[:-3] + '.html'
  mdTohtml(fileName, s)

mdTohtml('01 - Plateau1.md', '01 - Plateau1.html')
