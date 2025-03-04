# Makefile to clean and compile java files

# java files to compile
SRC = Frontend.java GameLoop.java

# clean target
clean:
	rm -f *.class out/*.class

# compile all java files
compile: $(SRC)
	javac -d out $(SRC)

# default
.DEFAULT_GOAL := compile
