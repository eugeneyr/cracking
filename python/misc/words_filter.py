def filterWords(inputFileName, outputFileName, wordLength):
    count = 0
    with open(inputFileName, 'r') as input:
        with open(outputFileName, 'w') as output:
            lines = input.readlines()
            for line in lines:
                line = line.strip().lower()
                if len(line) == wordLength:
                    output.write(line + '\n')
                    count += 1
            print('{} words of length {}'.format(count, wordLength))
    return count



if __name__ == '__main__':
    for i in range(1, 50):
        count = filterWords('../../data/words_long.txt', '../../data/words_long_{}.txt'.format(i), i)
        if count == 0:
            break
