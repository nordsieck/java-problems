.PHONY: clean
clean:
	-rm *.class
	-rm *~

.PHONY: test
test:
	ls *.java | xargs javac -cp junit-platform-console-standalone-1.4.2.jar:src
	java -jar junit-platform-console-standalone-1.4.2.jar -cp src --scan-class-path