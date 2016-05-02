n = int(input())
for test in range(n):
    s = input()
    cap = False
    if 'A' <= s[0] <= 'Z':
        cap = True
    s = s.lower()
    m = len(s)
    res = ""
    i = 0
    while i < m:
        if i == 0 and s[i] == 'e':
            res += "ae"
        elif i != 0 and s[i] == 's' and (i == m - 1 or s[i + 1] != 'h'):
            res += "th"
        elif s[i] == "o":
            j = i
            while j < m and s[j] == 'o':
                j += 1
            if j > i + 1:
                res += "ou" + "o" * (j - i - 2)
            else:
                res += "o"
            i = j - 1
        else:
            res += s[i]
        i += 1
    if cap:
        res = res[0].upper() + res[1:]
    print(res)