import sys

input = sys.stdin.readline


def isPalindrome(s, left, right):

    while left < right:
        if s[left] != s[right]:
            return False
        left += 1
        right -= 1

    return True


def check(s):
    left, right = 0, len(s) - 1

    while left < right:
        if s[left] != s[right]:
            if isPalindrome(s, left + 1, right) or isPalindrome(s, left, right - 1):
                return 1
            else:
                return 2
        left += 1
        right -= 1

    return 0


ans = []

t = int(input())

while t:
    ans.append(check(input().strip()))
    t -= 1

print("\n".join(map(str, ans)))
