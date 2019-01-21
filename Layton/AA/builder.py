import json

n = int(input("Starting ID: "))

while True:
  x = {"id": n}
  t = input("Type: ")
  while t != "P" and t != "T":
    print("INVALID TYPE")
    t = input("Type: ")
  x['type'] = t
  x['title'] = input("Title: ")
  l = []
  s = input('Next Step: ')
  while s != "":
    if s == "hint":
      y = []
      s = input("Hint Coin: ")
      while s != "":
        y.append(s)
        s = input("Hint Coin: ")
      l.append(y)
    else:
      l.append(s)
    s = input("Next Step: ")
  x['steps'] = l
  # Solution
  if t == "P":
    x['num'] = input("Puzzle Number: ")
    l = []
    s = input("Solution Image: ")
    while s != "":
      p = input("Solution Step: ")
      l.append({"img": s, "txt": p})
      s = input("Solution Image: ")
    x['solution'] = l
  p = input("Previous Text: ")
  x['prev'] = {"link": n - 1, "txt": p}
  p = input("Next Text: ")
  x['next'] = {"link": n + 1, "txt": p}

  if t == "P":
    f = "%s - P%s.json" % (n, x['num'])
  else:
    f = input("File: ")
  with open('JSON/%s' % f, 'w') as o:
    o.write(json.dumps(x))
  n += 1
