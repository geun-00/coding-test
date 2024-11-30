import sys
from collections import defaultdict

input = sys.stdin.readline

n, m = map(int, input().split())

team = {}
team_members = defaultdict(list)

for _ in range(n):

    team_name = input().strip()
    member = int(input().strip())

    for _ in range(member):

        name = input().strip()

        team[name] = team_name
        team_members[team_name].append(name)

for members in team_members.values():
    members.sort()

ans = []

for _ in range(m):

    name = input().strip()
    num = int(input().strip())

    if num == 0:
        ans.extend(team_members[name])
    else:
        ans.append(team[name])

print("\n".join(ans))
