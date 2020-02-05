import json

j = []
nums = []

for i in range(1,5):
  nums.append("0%s" % i)

for i in range(10,10):
  nums.append("%s" % i)

for i in nums:
  j.append({
    "img": "099S%s.png" % i,
    "txt": "Second and thurd items from left to empty slots"
  })

with open('out.json', 'w') as f:
  f.write(json.dumps(j, indent=2))
