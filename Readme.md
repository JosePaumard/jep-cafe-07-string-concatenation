## JEP Café episode 7: String Concatenation

Here is the code used in [JEP Café 4](https://youtu.be/w56RUzBLaRE), where you will find the class I used to get the benchmarks. 

This project is a Maven project, so you need a Maven installation to run it. 

### Running the benchmark using Java 17

To run the benchmark: 
1. change directory to the root directory of this project. 
2. make sure that you are using Java 17. You can check that by typing the following command in a console:
```shell
> java -version
```
3. Use maven to clean and package this project:
```shell
> mvn clean package
```
4. Then type in the following command:
```shell
> java -jar target/benchmarks.jar
```

Running the whole benchmark takes approximately 5mn on my desktop PC. The results you will get will be different than the one I have shown in the video. 

### Running the benchmark using Java 8

To run the benchmark using Java 8, you need two things:
1. edit the java version in the `pom.xml` file of the project, from `17` to `8`. 
2. make sure that you are using Java 8 with the command:
```shell
> java -version
```
You can then follow the previous instructions: cleaning and packaging your project, as well as running it are the same.  

### Warning

Let me copy / paste the warning JMH displays with every result it gives you: 

> REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.
 
Diminishing the error can usually be done by increasing the number of iterations in the measurements and the warmup annotation on the `StringConcat` class. Your mileage may vary. 