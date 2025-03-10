# Research Report

## Command Line Loop testing

### Summary of Work

I researched how to test the frontend commandline code for our game 

### Motivation

Command line code is not like other code because it is hard to interact directly with the console in testing, not like when you have a class or an array and you can interact with it directly, A Gameloop requires extra steps

### Time Spent

about 45 minutes

### Results

I refactored the GameLoop file so that rather than being basically a static class with main inside, I modified it so that now a GameLoop object can be instantiated with a passed input stream (there is also the default constructor which creates its own scanner which is what the gameloop will continue to use)

With this new version I then created a PlayerTester.java this class uploads an input stream through the System.setIn method and now the tester can pass input for the command line to use and we can test output using ByteArrayOutputStream() and verify that it is correct without having to constantly restart new instances of the loop and test it ourselves.

### Sources

- Grok
- Javadocs - System Class

[^1]: https://grok.com/chat/53d56300-f117-482c-af01-ca0a9fdc3a4e?referrer=website
[^2]: https://docs.oracle.com/javase/8/docs/api/java/lang/System.html
