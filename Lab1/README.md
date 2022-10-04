Compulsory (1p)

Create a servlet that receives a word and returns an HTML page containing the letters of that word presented as an
ordered list.

Done - created servlet with this functionality and also handled exception for null word (WordServlet)

Homework (2p)

Modify the servlet above such that:

It receives an integer as a parameter, called size, and it returns all the permutations of length size of the given
letters. If the size is 0 (default), it will return all the sequences.
If the servlet has access to a server-side file containing a list of acceptable words (a dictionary), it will return
only the sequences forming valid words.

Done - created a new servlet to also keep the compulsory one (ModifiedWordServlet)

Created all permutations of length size for comparing
Created a dictionary using aspell (words.txt) and comparing letter sequences to see which are valid words

