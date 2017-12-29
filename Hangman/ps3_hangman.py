# 6.00 Problem Set 3
# 
# Hangman game
#

# -----------------------------------
# Helper code
# You don't need to understand this helper code,
# but you will have to know how to use the functions
# (so be sure to read the docstrings!)

import random
import string

WORDLIST_FILENAME = "/Users/user/Downloads/Hangman/words.txt"

def loadWords():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print "Loading word list from file..."
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r', 0)
    # line: string
    line = inFile.readline()
    # wordlist: list of strings
    wordlist = string.split(line)
    print "  ", len(wordlist), "words loaded."
    return wordlist

def chooseWord(wordlist):
    """
    wordlist (list): list of words (strings)

    Returns a word from wordlist at random
    """
    return random.choice(wordlist)

# end of helper code
# -----------------------------------

# Load the list of words into the variable wordlist
# so that it can be accessed from anywhere in the program
wordlist = loadWords()

def isWordGuessed(secretWord, lettersGuessed):
    '''
    secretWord: string, the word the user is guessing
    lettersGuessed: list, what letters have been guessed so far
    returns: boolean, True if all the letters of secretWord are in lettersGuessed;
      False otherwise
    '''
    count=0
    wl= len(secretWord)
    for ch in secretWord:
        for l in lettersGuessed:
            if l==ch:
                count+=1
    if count==wl:
        return True
    return False
 
    


def getGuessedWord(secretWord, lettersGuessed):
    '''
    secretWord: string, the word the user is guessing
    lettersGuessed: list, what letters have been guessed so far
    returns: string, comprised of letters and underscores that represents
      what letters in secretWord have been guessed so far.
    '''
    guessedSoFar=[]
    for l in range(len(secretWord)):
        if (secretWord[l] in lettersGuessed):
            guessedSoFar.append(secretWord[l]+" ")
        else:
            guessedSoFar.append("_ ")
    letter=''
    for i in guessedSoFar:
        letter+=i
    
    return letter

def getAvailableLetters(lettersGuessed):
    '''
    lettersGuessed: list, what letters have been guessed so far
    returns: string, comprised of letters that represents what letters have not
      yet been guessed.
    '''
    available=''
    all=string.ascii_lowercase
    for l in range(len(all)):
        if all[l] not in lettersGuessed:
            available+=all[l]
    return available
    

def hangman(secretWord):
    '''
    secretWord: string, the secret word to guess.

    Starts up an interactive game of Hangman.

    * At the start of the game, let the user know how many 
      letters the secretWord contains.

    * Ask the user to supply one guess (i.e. letter) per round.

    * The user should receive feedback immediately after each guess 
      about whether their guess appears in the computers word.

    * After each round, you should also display to the user the 
      partially guessed word so far, as well as letters that the 
      user has not yet guessed.

    '''
    guesses_left = 8
    lettersGuessed = []
    print 'Welcome to the game, Hangman!'
    print 'I am thinking of a word that is %s letters long.' % (len(secretWord))

    while guesses_left >= 0:
        print '-------------'
        if isWordGuessed(secretWord, lettersGuessed):
            guesses_left = -1
            print 'Congratulations, you won!'
        elif guesses_left == 0:
            guesses_left = -1
            print 'Sorry, you ran out of guesses. The word was %s.' % (secretWord)
        else:
            print 'You have %s guesses left.' % (guesses_left)
            print 'Available letters: %s' % (getAvailableLetters(lettersGuessed))
            guess = raw_input('Please guess a letter:').lower()
            if guess in lettersGuessed:
                print "Oops! You've already guessed that letter: %s" % (getGuessedWord(secretWord, lettersGuessed))
            elif guess in secretWord:
                lettersGuessed.append(guess)
                print 'Good guess: %s' % (getGuessedWord(secretWord, lettersGuessed))
            else:
                lettersGuessed.append(guess)
                guesses_left -= 1
                print "Oops! That letter is not in my word: %s" % (getGuessedWord(secretWord, lettersGuessed))


secretWord = chooseWord(wordlist).lower()
hangman(secretWord)
