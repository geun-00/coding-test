def compress(s, length):

    str_list = []

    for i in range(0, len(s), length):
        str_list.append(s[i:i + length])

    sb = []
    prev = ""
    count = 0

    for now in str_list:
        if now == prev:
            count += 1
        else:
            if count >= 2:
                sb.append(str(count))
            sb.append(prev)
            prev = now
            count = 1

    if count >= 2:
        sb.append(str(count))
    sb.append(prev)

    return len("".join(sb))

def solution(s):
    
    ans = float('inf')

    for length in range(1, len(s) + 1):
        ans = min(ans, compress(s, length))
    
    return ans