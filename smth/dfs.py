import sys
sys.setrecursionlimit(100000)
def dfs(s):
    global d
    color[s] = 'gray'
    components[d].append(s)
    for v in graph[s]:
        if color[v] == 'white':
            dfs(v)
    color[s] = 'black'
    return


n, m = map(int, input().split())
d = 0
color = ['white' for i in range(n)]
graph = [[] for i in range(n)]
for i in range(m):
    a, b = map(int, input().split())
    graph[a - 1].append(b - 1)
    graph[b - 1].append(a - 1)
components = []
for i in range(n):
    if color[i] == 'white':
        components.append([])
        dfs(i)
        d += 1
print(d)
for l in components:
    print(len(l))
    for x in l:
        print(x + 1, end=' ')
    print('')