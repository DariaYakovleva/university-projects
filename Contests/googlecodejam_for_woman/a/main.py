def merge(a, b):
    i = 0
    j = 0
    c = []
    while (len(a) > i and len(b) > j):
        if a[i] < b[j]:
            c.append(a[i])
            i += 1
        else:
            c.append(b[j])
            j += 1
    return c + a[i:] + b[j:]


def mergesort(a):
    if len(a) <= 1:
        return a
    else:
        left = a[:len(a) // 2]
        right = a[len(a) // 2:]
        return merge(mergesort(left), mergesort(right))


fin = open("sort.in")
fout = open("sort.out", "w")
n = int(fin.readline())
arr = [int(i) for i in fin.readline().strip().split()]
arr_ready = mergesort(arr)
v = list(map(int, input().split()))
for i in arr_ready:
    print(i, end=' ', file=fout)
fout.close()
