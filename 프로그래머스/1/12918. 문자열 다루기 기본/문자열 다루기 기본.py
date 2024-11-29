import re

def solution(s):
    return bool(re.fullmatch(r'\d{4}|\d{6}', s))