vowels = ['A', 'E', 'I', 'O', 'U']

def solve(word, words):
    
    words.append(word)
    
    if len(word) == 5:
        return
    
    for c in vowels:
        solve(word + c, words)

def solution(word):
    words = []
    
    solve("", words)
    
    return words.index(word)