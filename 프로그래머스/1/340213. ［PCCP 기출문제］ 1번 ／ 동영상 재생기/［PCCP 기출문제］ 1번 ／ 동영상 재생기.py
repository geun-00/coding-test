def convert(s):
    h, m = map(int, s.split(":"))
    return h * 60 + m

def pos_check(pos_min, start_min, end_min):
    if start_min <= pos_min <= end_min:
        return end_min
    return pos_min

def solution(video_len, pos, op_start, op_end, commands):
    
    video_min = convert(video_len)
    pos_min = convert(pos)
    start_min = convert(op_start)
    end_min = convert(op_end)
    
    pos_min = pos_check(pos_min, start_min, end_min)
    
    for com in commands:
        if com == 'prev':
            pos_min = max(0, pos_min - 10)
            pos_min = pos_check(pos_min, start_min, end_min)
        elif com == 'next':
            pos_min = min(pos_min + 10, video_min)
            pos_min = pos_check(pos_min, start_min, end_min)
            
    pos_min = pos_check(pos_min, start_min, end_min)
    
    minute = f"{pos_min // 60:02d}"
    second = f"{pos_min % 60:02d}"
    
    return f"{minute}:{second}"