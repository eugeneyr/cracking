def filterWords(inputFileName, outputFileName, wordLength):
    with open(inputFileName, 'r') as input:
        with open(outputFileName, 'w') as output:
            count = 0
            lines = input.readlines()
            for line in lines:
                line = line.strip().lower()
                if len(line) == wordLength:
                    output.write(line + '\n')
                    count += 1
            print('{} words of length {}'.format(count, wordLength))



if __name__ == '__main__':
    for i in range(2, 24):
        filterWords('../../data/words.txt', '../../data/words{}.txt'.format(i), i)
