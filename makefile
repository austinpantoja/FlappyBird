CC=javac
classpath=bin
main-class=game.FlappyBird

compile:
	rm -rf $(classpath)
	mkdir $(classpath)
	find src -name "*.java" | xargs $(CC) -d $(classpath) 

run:
	java -cp $(classpath) $(main-class)

clean:
	rm -rf $(classpath)
