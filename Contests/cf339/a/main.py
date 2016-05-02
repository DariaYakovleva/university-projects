l, r, k = map(int, input().split())
x = 1
while (x < l):
	x *= k
ok = False
while (x >= l and x <= r):
	print(x, end=' ')
	ok = True
	x *= k
if (not ok):
	print(-1)