"""
Word Rectangle:
Given a list of millions of words, design an algorithm to create the largest possible rectangle of letters
such that every row forms a word (reading from left to right) and every column forms a word (reading top to bottom).
THe words need not be chosen consecutively from the list but all rows must be the same length and all columns
must be the same height.
"""

import copy
import time


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


def getCrossWords(words: list):
    rv = []
    if len(words) > 0:
        wordLen = len(words[0])
        for i in range(0, wordLen):
            crossWord = ''
            for word in words:
                crossWord += word[i]
            rv.append(crossWord)
    return rv


def findLargestRectangle(wordList, minWidth=0, minHeight=0, outputFileName=None, ignoreSmaller=False):
    largestFound = 0
    bigTrie = TrieNode()
    triesByLength = {}
    wordsByLength = {}
    maxWordLength = 0
    found = []
    output = None

    def searchRectangles(length, height, words):
        nonlocal largestFound
        if ignoreSmaller and (height * length < largestFound):
            return
        # Stops the recursion: the rectangle cannot be larger than that
        if len(words) > height:
            return
        # If the number of the words == height, it is a valid rectangle. Store it and exit.
        if len(words) == height:
            if largestFound < height * length:
                largestFound = height * length
            found.append(copy.deepcopy(words))
            print('==== Found ====', file=output, flush=True)
            for word in words:
                print(word, file=output, flush=True)
            if output is not None:
                print('==== Found ====')
                for word in words:
                    print(word)
            return

        trie = triesByLength.get(height)
        if trie is not None:
            usedWords = set(words)
            availableWords = wordsByLength.get(length)
            if availableWords is not None:
                for word in availableWords:
                    if word not in usedWords:
                        # check if cross words of words + word are prefixes of existing words
                        words.append(word)
                        crossWords = getCrossWords(words)
                        allGood = True
                        for crossWord in crossWords:
                            if not trie.hasPrefix(crossWord):
                                allGood = False
                                break
                        if allGood:
                            # go one level deeper
                            searchRectangles(length, height, words)
                        words.pop()

    if outputFileName is not None:
        output = open(outputFileName, 'w')

    try:
        for word in wordList:
            bigTrie.addWord(word)
            trie = triesByLength.get(len(word), TrieNode())
            trie.addWord(word)
            triesByLength[len(word)] = trie
            words = wordsByLength.get(len(word), set())
            words.add(word)
            wordsByLength[len(word)] = words
            if len(word) > maxWordLength:
                maxWordLength = len(word)
        for i in range(maxWordLength, minWidth, -1):
            for j in range(i, minHeight, -1):
                start = time.time()
                print('Searching for rectangles {} x {}'.format(i, j))
                searchRectangles(i, j, [])
                end = time.time()
                print('Time spent: {:.3f} s'.format(end - start))
    finally:
        if output is not None:
            output.close()

    return found


def loadWordList(fileName, maxWordLength=None):
    words = set()
    rv = []
    with open(fileName, 'r') as file:
        lines = file.readlines()
        for line in lines:
            line = line.strip().lower()
            if line not in words and (maxWordLength is None or maxWordLength >= len(line)):
                rv.append(line)
                words.add(line)
    return rv


import unittest


class FindRectangleTest(unittest.TestCase):
    @unittest.skip
    def test_smallList(self):
        found = findLargestRectangle(['sator', 'arepo', 'tenet', 'opera', 'rotas'])
        for words in found:
            print('==== Found ====')
            for word in words:
                print(word)

    @unittest.skip
    def test_mediumlList(self):
        found = findLargestRectangle(loadWordList('../../data/words.txt', maxWordLength=5), minWidth=4, minHeight=4,
                                     ignoreSmaller=True)
        for words in found:
            print('==== Found ====')
            for word in words:
                print(word)

    def test_mediumlList_save7(self):
        found = findLargestRectangle(loadWordList('../../data/words.txt', maxWordLength=7), minWidth=6, minHeight=6,
                                     ignoreSmaller=True, outputFileName='../../data/rect_7x7.txt')
        self.assertTrue(len(found) > 0)

    @unittest.skip
    def test_mediumlList_save(self):
        found = findLargestRectangle(loadWordList('../../data/words.txt', maxWordLength=6), minWidth=5, minHeight=5,
                                     ignoreSmaller=True, outputFileName='../../data/rect_6x6.txt')
        self.assertTrue(len(found) > 0)

    @unittest.skip
    def test_biglList(self):
        found = findLargestRectangle(loadWordList('../../data/words.txt', maxWordLength=6), minWidth=5, minHeight=5)
        for words in found:
            print('==== Found ====')
            for word in words:
                print(word)


if __name__ == '__main__':
    unittest.main()
