compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt

bin:
	mkdir bin

jar:
	jar cfm ass7game.jar manifest.mf -C resources . -C bin .

run:
	java -cp biuoop-1.4.jar:bin:resources Ass7