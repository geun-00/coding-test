from collections import defaultdict
import sys
sys.setrecursionlimit(10**6)

class Node:
    def __init__(self):
        self.count_map = defaultdict(int)
        self.children = {}

    def add(self, word, index):
        if index >= len(word):
            return

        length = len(word) - index
        self.count_map[length] += 1

        c = word[index]
        if c not in self.children:
            self.children[c] = Node()

        self.children[c].add(word, index + 1)

    def count(self, query, index):
        if query[index] == '?':
            return self.count_map[len(query) - index]

        c = query[index]
        if c not in self.children:
            return 0

        return self.children[c].count(query, index + 1)

def solution(words, queries):
    
    trie = Node()
    reverseTrie = Node()

    for word in words:
        trie.add(word, 0)
        reverseTrie.add(word[::-1], 0)

    ans = []
    for query in queries:
        if query[0] == '?':
            ans.append(reverseTrie.count(query[::-1], 0))            
        else:
            ans.append(trie.count(query, 0))

    return ans