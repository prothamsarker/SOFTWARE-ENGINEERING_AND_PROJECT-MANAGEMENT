# Stack and Heap in Java

**Name:** SHAHRIER MOKAMMEL | **ID:** IT24050

## Files
- `StudentMemoryDemo.java` - Problem 1 (Student: instance variable, local variable, identity hash code)
- `EmployeeMemoryDemo.java` - Problem 2 (Employee with local inner class Report)

## How to run
```bash
javac StudentMemoryDemo.java
java StudentMemoryDemo

javac EmployeeMemoryDemo.java
java EmployeeMemoryDemo
```

## Output
![Student output](Output/student_output.png)

![Employee output](Output/employee_output.png)

## Memory concepts
- **Heap:** objects created with `new` (Student, Employee, Report) and their instance fields (`age`, `salary`).
- **Stack:** local variables (`marks`, `bonus`) and reference variables (`s`, `e`, `r`) live in method stack frames. `show()` runs in its own frame.
- `System.identityHashCode()` gives an object identity number, not the real memory address, so it differs between runs.
