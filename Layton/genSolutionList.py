import json

j = []
nums = []

for i in range(1,10):
  nums.append("0%s" % i)

for i in range(10,16):
  nums.append("%s" % i)

for i in nums:
  j.append({
    "img": "103S%s.png" % i,
    "txt": "Middle ball in third column down"
  })

with open('out.json', 'w') as f:
  f.write(json.dumps(j, indent=2))
