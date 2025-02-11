import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creating individual employees (Leaf nodes)
        Employee dev1 = new Employee("John Dev", "Developer", 75000);
        Employee dev2 = new Employee("Alice Code", "Developer", 70000);
        Employee designer = new Employee("Bob Art", "Designer", 65000);
        
        // Creating departments (Composite nodes)
        Department engineering = new Department("Engineering");
        Department design = new Department("Design");
        Department company = new Department("Tech Corp");
        
        // Building the hierarchy
        engineering.addEmployee(dev1);
        engineering.addEmployee(dev2);
        design.addEmployee(designer);
        
        company.addEmployee(engineering);
        company.addEmployee(design);
        
        // Display organization structure and costs
        System.out.println("Organization Structure:");
        company.showDetails("");
        
        System.out.println("\nTotal Annual Cost: $" + company.getAnnualCost());
    }
}

// Component interface
interface OrganizationComponent {
    void showDetails(String indent);
    double getAnnualCost();
}

// Leaf class
class Employee implements OrganizationComponent {
    private String name;
    private String position;
    private double salary;
    
    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    
    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "└─ " + name + " (" + position + ") - $" + salary);
    }
    
    @Override
    public double getAnnualCost() {
        return salary;
    }
}

// Composite class
class Department implements OrganizationComponent {
    private String name;
    private List<OrganizationComponent> employees = new ArrayList<>();
    
    public Department(String name) {
        this.name = name;
    }
    
    public void addEmployee(OrganizationComponent employee) {
        employees.add(employee);
    }
    
    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "├─ " + name + " Department");
        for (OrganizationComponent employee : employees) {
            employee.showDetails(indent + "   ");
        }
    }
    
    @Override
    public double getAnnualCost() {
        return employees.stream()
                       .mapToDouble(OrganizationComponent::getAnnualCost)
                       .sum();
    }
}