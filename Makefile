.PHONY: clean
clean:
	git clean -xdf

build:
	find src | grep java | xargs javac -d bin -cp junit-platform-console-standalone-1.4.2.jar

.PHONY: test
test:
	java -jar junit-platform-console-standalone-1.4.2.jar -cp bin --fail-if-no-tests --scan-class-path
