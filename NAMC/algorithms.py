# This file contains the training algorithms which are used to alter the weights of the text in order to bias the text in a certain way
# Version 1.3
# Author: Met Dekaj

import random

#This weight favours simplicity in the text
def aw_favor_simplicity(a, b):
    return len(set([c for c in a + b])) / len(a + b)

#This weight favours an altering mode of complexity and simplicity in the text
def aw_favor_alternating_complexity(a, b):
    return (aw_favor_simplicity(b, b) + aw_favor_complexity(a, a)) / 2

#This weight favours the use of punctuation in the text
def aw_favor_punctuation(a, b):
    return sum(map(lambda x: 0.5 if x[-1] in '.,;?!:()[]{}' else 0, [a, b]))

#This weight favours complexity in the text
def aw_favor_complexity(a, b):
    return 1 - aw_favor_simplicity(a, b)

#This weight favours rhyming in the text
def aw_favor_rhymes(a, b):
    a, b = sorted([a, b], key=min)
    return sum([1 if p[0] == p[1] else 0 for p in zip(b[len(b) - len(a):], a)]) / len(a)

#This weight favours alliteration in the text
def aw_favor_alliterations(a, b):
    a, b = sorted([a, b], key=min)
    return sum([1 if p[0] == p[1] else 0 for p in zip(b[:len(b) - len(a)], a)]) / len(a)

# Returns a fitness function multiplied by a constant k
def aw_mul(f, k):
    return lambda a, b: f(a, b) * k

#This weight favours the use of vowels in the text
def aw_favor_vowels(a, b):
    return sum([1 if c in 'aeiouy' else 0 for c in a + b]) / len(a + b)

#This weight favours illegibility in the text
def aw_favor_illegibility(a, b):
    return 1 - aw_favor_punctuation(a, b)

#This weight favours randomness in the text
def aw_random(a, b):
    return random.random()

#This weight favours the use of consonants in the text
def aw_favor_consonants(a, b):
    return 1 - aw_favor_vowels(a, b)
