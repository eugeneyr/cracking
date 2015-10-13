"""
Word Rectangle:
Given a list of millions of words, design an algorithm to create the largest possible rectangle of letters
such that every row forms a word (reading from left to right) and every column forms a word (reading top to bottom).
THe words need not be chosen consecutively from the list but all rows must be the same length and all columns
must be the same height.
"""


class TrieNode:
    def __init__(self):
        self.letters = {}
        self.stopper = False

    def addWord(self, word):
        if word is None:
            return
        if len(word) == 0:
            self.stopper = True
            return
        head = word[0]
        tail = word[1:]
        if head in self.letters:
            nextNode = self.letters[head]
        else:
            nextNode = TrieNode()
            self.letters[head] = nextNode
        nextNode.addWord(tail)

    def hasWord(self, word):
        if len(word) == 0:
            return self.stopper
        head = word[0]
        tail = word[1:]
        if head in self.letters:
            return self.letters[head].hasWord(tail)
        return False

    def hasPrefix(self, word):
        if len(word) == 0:
            return True
        head = word[0]
        tail = word[1:]
        if head in self.letters:
            return self.letters[head].hasPrefix(tail)
        return False


def loadTrie(fileName):
    with open(fileName, 'r') as file:
        lines = file.readlines()
        root = TrieNode()
        for line in lines:
            root.addWord(line.strip().lower())
        return root

def getCrossWords(words:list):
    rv = []
    if len(words) > 0:
        wordLen = len(words[0])
        for i in range(0, wordLen):
            crossWord = ''
            for word in words:
                crossWord += word[i]
            rv.append(crossWord)
    return rv



import unittest


class TrieNodeTest(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.trie = loadTrie('../../data/words.txt')
        cls.longList = set()
        with open('../../data/words_long.txt', 'r') as file:
            lines = file.readlines()
            for line in lines:
                cls.longList.add(line.strip().lower())
        cls.shortList = set()
        with open('../../data/words.txt', 'r') as file:
            lines = file.readlines()
            for line in lines:
                cls.shortList.add(line.strip().lower())

    def test_notInList(self):
        for line in TrieNodeTest.longList:
            inShort = line in TrieNodeTest.shortList
            self.assertEqual(inShort, TrieNodeTest.trie.hasWord(line), line)

    def test_prefixes(self):
        for line in TrieNodeTest.shortList:
            prefix = line[:-1]
            self.assertTrue(TrieNodeTest.trie.hasPrefix(line), line)
            self.assertTrue(TrieNodeTest.trie.hasPrefix(prefix), line)
            self.assertFalse(TrieNodeTest.trie.hasPrefix(prefix + '$'), line)

class CrossWordTest(unittest.TestCase):
    def testCrossWord(self):
        cw = getCrossWords(['ac', 'bd'])
        self.assertEqual(cw, ['ab', 'cd'])


if __name__ == '__main__':
    unittest.main()
