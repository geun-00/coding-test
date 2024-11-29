import re


def solution(s):
    s = s.lower()

    s = re.sub(r"[^a-z0-9\-_.]", "", s)

    s = re.sub(r"\.{2,}", ".", s)

    s = re.sub(r"^\.|\.$", "", s)

    if not s:
        s = "a"

    if len(s) >= 16:
        s = s[:15]
        s = re.sub(r"\.$", "", s)

    while len(s) < 3:
        s += s[-1]

    return s