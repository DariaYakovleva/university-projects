import random
f = open('test1.txt', 'w')
c = 10
for i in range(1000):
	c = c * 10
a = random.randint(0, c)
b = random.randint(0, c)
f.write(str(a)+'\n')
f.write(str(b)+'\n')
f.close()
f = open('test1.gold', 'w')
f.write(str(a*b)+'\n')
f.close()
