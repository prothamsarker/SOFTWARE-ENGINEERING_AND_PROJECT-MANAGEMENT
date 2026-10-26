import java.util.Scanner;

public class EmployeeMemoryDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter salary: ");
        int salary = sc.nextInt();
        System.out.print("Enter bonus: ");
        int bonus = sc.nextInt();
        System.out.println();

        Employee e = new Employee(salary);   // 'e' -> stack of main(); object -> heap
        System.out.println("Employee Object Created");
        System.out.println("Employee Identity Hash Code: " + System.identityHashCode(e));
        System.out.println();

        e.display(bonus);

        System.out.println();
        System.out.println("Memory Concept:");
        System.out.println("Employee object -> Heap");
        System.out.println("Report object -> Heap");
        System.out.println("salary -> Instance field");
        System.out.println("bonus -> Local variable in display()");
        System.out.println("show() -> Executes in its own method frame");

        sc.close();
    }
}

// Heap object: each Employee object (with its 'salary' field) lives on the heap.
class Employee {
    int salary;                    // instance variable -> inside the object on the heap

    Employee(int salary) {
        this.salary = salary;
    }

    void display(int bonusInput) {
        int bonus = bonusInput;    // local variable -> in display()'s stack frame

        System.out.println("Inside display() Method:");
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);
        System.out.println();

        // Local inner class: declared inside the method
        class Report {
            void show() {
                // show() runs in its own stack frame
                System.out.println("Report: Employee salary is " + salary);
            }
        }

        Report r = new Report();   // Report object -> heap; 'r' (reference) -> display()'s stack frame
        System.out.println("Report Object Created");
        System.out.println("Report Identity Hash Code: " + System.identityHashCode(r));
        r.show();
    }
}
