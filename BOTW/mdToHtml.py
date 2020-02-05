count = -1

def nextLine(lines):
  try:
    return lines.pop(0)
  except:
    return False

def inputCheck(text):
  global count
  if input('Input %s: ' % text) != 'N':
    count += 1
    return '<li class="check"><input type="checkbox" id="c%s" onclick="checkClick(this)"/><label for="c%s">%s</label>' % (count, count, text)
  else:
    return '<li>%s' % text

def mdTohtml(mdURL, htmlURL):
  global count
  lines = open(mdURL).read().splitlines()

  line = nextLine(lines)
  output = '<html><head><style>.check{list-style:none;position:relative;left:-7px;}li{list-style-position:inside}</style><script type="text/javascript">var state=['

  body = '<body>'
  while line != False:
    if line.startswith('#'):
      i = 1
      for char in line[1:]:
        if(char == '#'):
          i += 1
        else:
          break
      body += '<h%s>%s</h%s>' % (i, line[i + 1:], i)
    elif line.startswith('!['):
      vals = line.split('](')
      body += '<img alt="%s" src="%s"/><br><br>' % (vals[0][2:], vals[1][:-1])
    elif line.startswith('* '):
      body += '<ul>%s' % inputCheck(line[2:])
      line = nextLine(lines)
      depth = 0
      while line:
        if line.startswith(('  ' * depth) + '* '):
          body += '</li>%s' % inputCheck(line[(depth + 1) * 2:])
        elif line.startswith(('  ' * (depth + 1)) + '* '):
          depth += 1
          body += '<ul>%s' % inputCheck(line[2 * (depth + 1):])
        else:
          for i in range(0, depth):
            if line.startswith(('  ' * i) + '* '):
              body += '</li></ul>' * (depth - i)
              body += inputCheck(line[2 * (i + 1):])
              depth = i
              break
        line = nextLine(lines)
      body += '</li></ul>' + ('</li></ul>' * depth)
    elif line != '':
      body += '<p>%s</p>' % line
    line = nextLine(lines)
  body += '</body>'

  for i in range(0, count):
    output += '0,'
  output += '0]; windown.onload = function(){state.forEach((item,index)=>{document.getElementById("c"+index).checked=!!item;})}; function checkClick(check){state[parseInt(check.id.substr(1))]=check.checked?1:0;}</script></head>'

  output += body + '</html>'

  with open(htmlURL, 'w') as f:
    f.write(output)

files = ['17 - Champion\'s Ballad.md', '18 - Lanaryu2.md', '19 - Hateno3.md', '20 - Hylia1.md',
  '21 - Faron1.md', '22 - Central2.md', '23 - Hyrule Castle.md', '24 - PostGame.md']

for fileName in files:
  count = -1
  s = fileName[:-3] + '.html'
  print('Start: ' + fileName)
  mdTohtml(fileName, s)
  print(fileName + ' => ' + s)
