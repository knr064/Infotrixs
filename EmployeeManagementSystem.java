import java.io.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private int age;
    private String designation;
    private int salary;

    public Employee(int id, String name, int age, String designation, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDesignation() {
        return designation;
    }
     public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_PATH = "employees.txt";
    private List<Employee> employees;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
        loadEmployeesFromFile();
    }

    public void addEmployee(Employee employee) {
        if (isEmployeeExists(employee.getId())) {
            System.out.println("Employee with ID " + employee.getId() + " already exists.");
            return;
        }

        employees.add(employee);
        saveEmployeesToFile();
        System.out.println("Employee added successfully.");
    }

    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employee List:");
            for (Employee employee : employees) {
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getName());
                System.out.println("Age: " + employee.getAge());
                System.out.println("Designation: " + employee.getDesignation());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("------------------------");
            }
        }
    }

    public void updateEmployee(int id, String name, int age, String designation, int salary) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setName(name);
                employee.setAge(age);
                employee.setDesignation(designation);
                employee.setSalary(salary);
                saveEmployeesToFile();
                System.out.println("Employee updated successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public void deleteEmployee(int id) {
        for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext(); ) {
            Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                saveEmployeesToFile();
                System.out.println("Employee deleted successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public void searchEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("Employee found:");
                System.out.println("---------------");
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getName());
                System.out.println("Age: " + employee.getAge());
                System.out.println("Designation: " + employee.getDesignation());
                System.out.println("Salary: " + employee.getSalary());
                System.out.println("------------------------");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    private boolean isEmployeeExists(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split("	");
                int id = Integer.parseInt(employeeData[0]);
                String name = employeeData[1];
                int age = Integer.parseInt(employeeData[2]);
                String designation = employeeData[3];
                int salary = Integer.parseInt(employeeData[4]);
                employees.add(new Employee(id, name, age, designation,salary));
            }
        } catch (IOException e) {
            System.out.println("Error loading employees from file: " + e.getMessage());
        }
    }

    private void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "	" + employee.getName() + "	" + employee.getAge() + "	" +
                        employee.getDesignation() +"	"+employee.getSalary() +"\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving employees to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManagementSystem managementSystem = new EmployeeManagementSystem();

        while (true) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. List Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int id = scanner.nextInt();
                    if (managementSystem.isEmployeeExists(id)) {
                        System.out.println("Employee with ID " + id + " already exists. Please provide valid data.");
                        break;
                    }
                    System.out.print("Enter employee name: ");
                    String name = scanner.next();
                    System.out.print("Enter employee age: ");
                    int age = readIntegerInput(scanner);
                    System.out.print("Enter employee designation: ");
                    String designation = scanner.next();
                    System.out.print("Enter employee salary: ");
                    int salary = readIntegerInput(scanner);
                    Employee employee = new Employee(id, name, age, designation, salary);
                    managementSystem.addEmployee(employee);
                    break;
                case 2:
                    managementSystem.listEmployees();
                    break;
                case 3:
                    System.out.print("Enter employee ID: ");
                    int updateId = scanner.nextInt();
                    if (!managementSystem.isEmployeeExists(updateId)) {
                        System.out.println("Employee with ID " + updateId + " Doesn't exists. Please provide valid data.");
                        break;
                    }
                    System.out.print("Enter updated employee name: ");
                    String updateName = scanner.next();
                    System.out.print("Enter updated employee age: ");
                    int updateAge = readIntegerInput(scanner);
                    System.out.print("Enter updated employee designation: ");
                    String updateDesignation = scanner.next();
                    System.out.print("Enter updated employee salary: ");
                    int updateSalary = readIntegerInput(scanner);
                    managementSystem.updateEmployee(updateId, updateName, updateAge, updateDesignation, updateSalary);
                    break;
                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    managementSystem.deleteEmployee(deleteId);
                    break;
                case 5:
                    System.out.print("Enter employee ID to search: ");
                    int searchId = scanner.nextInt();
                    managementSystem.searchEmployee(searchId);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int readIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}

