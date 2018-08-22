#!/usr/bin/python
# -*- coding: utf-8 -*-

# Short story teller which generates text by collating probabilities from textual data, its characteristics are defined
# by the training algorithms in the algorithms.py file
# Version 1.3
# Author: Met Dekaj

# Importing other files
import os
from chain import *
from algorithms import *

# Builds a formatted string from the generator
def gen(m):
    return ''.join([w for w in m.generate_formatted(word_wrap=60, soft_wrap=True, start_with=None, max_len=100, verbose=True)])


# Initialize the chain and trains it on the data set located in 'training_data' using absoulte paths
# Make sure to correct these paths when using another computer or it wont work!
mkv = Chain()
mkv.bulk_train('/Users/metdekaj/NAMC/training_data/*', verbose=True, batches=100, iterations=1000)

# Or use this line below to train just one file at a time
# mkv.load_training('/Users/metdekaj/NAMC/training_data/Filename)

# Adjust the weights with the help of some fitness functions.
mkv.bulk_adjust_weights(fitness_functions=[aw_mul(aw_favor_simplicity, 0.5), aw_mul(aw_random, 0.1), aw_mul(aw_favor_punctuation, 0.5), aw_mul(aw_favor_rhymes, 0.5)],
                        iterations=10000,
                        verbose=True)


# Store this information, so there is no need to re-train
# Make sure to correct these paths when using another computer or it wont work!
mkv.save_training('/Users/metdekaj/NAMC/stored_data/training')


# Saving this state in a new file to prevent feedback loops
# Make sure to correct these paths when using another computer or it wont work!
mkv.save_training('/users/metdekaj/NAMC/stored_data/training01')

# Print out output after all weights have been adjusted. This prints out 3 times each with different starting words. To create more texts simply repeat the last line of code.
print(gen(mkv))
print(gen(mkv))
print(gen(mkv))
