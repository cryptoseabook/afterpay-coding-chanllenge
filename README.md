# Afterpay coding test

### coding requirement

Consider the following credit card fraud detection algorithm
A credit card transaction is comprised of the following elements.
hashed credit card number
timestamp - of format 'year-month-dayThour:minute:second'
amount - of format 'dollars.cents'
Transactions are to be received as a comma separated string of elements.
eg. 10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00
A credit card will be identified as fraudulent if the sum of amounts for a unique hashed credit card number
over a 24 hour sliding window period exceeds the price threshold.
Write a method, which when given a sequence of transactions in chronological order, and a price threshold,
returns the hashed credit card numbers that have been identified as fraudulent.

### Implementation walk through
- created four classes, for creditcardtxn entity, main class, Utils class for all common methods and main fraud detection class
- the tricky part is how do you do rolling 24 hours, my implementation used lots of java 8 stream library to make the code
more concise. Java is notorious verbose even those stream apis. 
- require double for loop to do rolling 24 hours window, there might be a more performant way to do this
- apart from that is pretty straight forward.
- I added a couple test cases

### Author info
- seabook chen, 15 years dev exp, familiar with java, nodejs, go, blockchain etc


```$xslt
Any fool can write code that a computer can understand. Good programmers write code that humans
can understand.
- Martin Fowler
```