# Calculator
Calculator program made in Java. Simple calculator that does
simple arithmetic with addition, subtraction, dvision, 
mulitplication (with multiply operator or with parenthesis), and exponents.
Run EvaluatorUI class to use calculator.

## Compiling Project from command line

### Windows
```
javac -d target .\calculator\evaluator\Operand.java
javac -d target .\calculator\operators\*.java
javac -d target .\calculator\evaluator\*.java
```

### Linux/MacOS
```
javac -d target ./calculator/evaluator/Operand.java
javac -d target ./calculator/operators/*.java
javac -d target ./calculator/evaluator/*.java
```

## Run Project One

### Windows/Linux/MacOS
```
java -cp target calculator.evaluator.Evaluator
```

## Running Unit Tests for Project One

### Compile Test

### Windows
```
javac -d target --class-path "./;.\lib\junit-platform-console-standalone-1.9.3.jar" .\tests\operator\*.java
javac -d target --class-path "./;.\lib\junit-platform-console-standalone-1.9.3.jar" .\tests\operand\*.java
javac -d target --class-path "./;.\lib\junit-platform-console-standalone-1.9.3.jar" .\tests\*.java

```

### Linux/MacOS
```
junit-platform-console-standalone-1.9.3.jar" ./tests/operator/*.java
javac -d target --class-path "./:./lib/junit-platform-console-standalone-1.9.3.jar" ./tests/operand/*.java
javac -d target --class-path "./:./lib/junit-platform-console-standalone-1.9.3.jar" ./tests/*.java
```

## Run Unit Test

### Windows
```
java -jar .\lib\junit-platform-console-standalone-1.9.3.jar -cp target --scan-classpath
```

### Linux
```
java -jar ./lib/junit-platform-console-standalone-1.9.3.jar -cp target --scan-classpath
```
