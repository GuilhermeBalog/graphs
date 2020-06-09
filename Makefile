default:
	javac graphs/*.java && java graphs/Main 1 2 6
out:
	javac *.java && java Main > result.txt