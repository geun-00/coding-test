from itertools import combinations

s = input()

stk = []
pairs = []

for i, ch in enumerate(s):
    if ch == '(':
        stk.append(i)
    elif ch == ')':
      pairs.append((stk.pop(), i))


ans = set()

for i in range(1, len(pairs) + 1):
    for comb in combinations(pairs, i):
        temp = list(s)

        for open_idx, close_idx in comb:
            temp[open_idx] = ' '
            temp[close_idx] = ' '

        ans.add(''.join(temp).replace(' ', ''))

for result in sorted(ans):
    print(result)
