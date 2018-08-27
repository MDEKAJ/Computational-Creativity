# Computational-Creativity
Project Description 
In this project, I will be producing a creative system which will write short stories. 
For my creative system, I will be using Markov process to locally generate text. Markov process is a probabilistic process where the state of the system at a particular time is dependent on the state of the system at a fixed (usually small) number of prior timesteps [1]. This will be done by creating a probability matrix which displays certain words and their probability to appear based on the previous word. The high the number of timesteps, the better the simulation – however as the number of timesteps increases, so does the length of the test data. The probability table will be used as the basis for the text generation when developing the creative system, as the story will draw words from the table based on their probability to show up. This process of generating text is based on the ‘Markov Property’ theory [2]. I aim to use fitness functions to adjust the weights of the probabilities by ‘weakening’ or ‘strengthening’ each weight similar to neural networks.  This will help with plot direction as it will allow me to bias certain words or phrases at different stages of the story, thus creating a more coherent story.Depending on time restrictions, it is also an aim of mine to also combine this corpus-based analysis will Grammar-based text generation in order to create grammatically correct common phrases.

Project Evaluation 
I will evaluate my practical work using the SPECS evaluation process. SPECS (Standardised Procedure for Evaluating Creative Systems) is an evaluation process which consists of three steps; Investigation, Standards of Evaluation and Testing. 
I have decided to use SPECS as it is flexible enough to evaluate any creative system. It is also a standardised procedure of evaluation, and so there are likely to be many other story-telling systems evaluated with this method –which would be useful when comparing. Another reason as to why I have chosen SPECS is that it covers evaluation systems in a systematic way. As shown in the lecture notes [4], most systems are not evaluated in a systematic or standardized way –SPECS ensures that the evaluation process is thorough. 
In order for the system to be considered a success or a creative system, it has to perform well in the Testing stage of the SPECS evaluation. I will also be evaluating other story-telling systems in my report and comparing my approach to other systems.


References: 
[1] CC_WK16_Lecture1, Page 6 – Markov processes
[2] Bertsekas, D. (2017). Introduction to Probability. London: Facts101.
[3] W15_1+2evaluation_of_creativity, Page 11 –Evaluation of creative systems: A review of the situation in 2010.
