package test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {

    private static final String XML_FILE = "dept-info.xml";

    public static void main(String[] args) {

        Employee emp1 = new Employee("E01", "Tom", null);
        Employee emp2 = new Employee("E02", "Mary", "E01");
        Employee emp3 = new Employee("E03", "John", null);

        Employee emp4 = new Employee("E04", "Mike", null);
        Employee emp5 = new Employee("E05", "Kate", "E04");

        List<Employee> list = new ArrayList<Employee>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);

        List<Employee> list1 = new ArrayList<Employee>();
        list1.add(emp4);
        list1.add(emp5);

        Department dept = new Department("D01", "ACCOUNTING", "NEW YORK");
        Department dept2 = new Department("D02", "MANAGEMENT", "WASHINGTON");
        dept.setEmployees(list);
        dept2.setEmployees(list1);
        List<Department> list2 = new ArrayList<Department>();
        list2.add(dept);
        list2.add(dept2);

        Organisation org = new Organisation("O01", "ACME", "USA");
        org.setDepartments(list2);


        try {
            // create JAXB context and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(Organisation.class);

            // (1) Marshaller : Java Object to XML content.
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(org, System.out);

            // Write to File
            File outFile = new File(XML_FILE);
            m.marshal(org, outFile);

            System.err.println("Write to file: " + outFile.getAbsolutePath());
            // (2) Unmarshaller : Read XML content to Java Object.
            Unmarshaller um = context.createUnmarshaller();

            // XML file create before.
            Organisation orgFromFile = (Organisation) um.unmarshal(new FileReader(XML_FILE));
            List<Department> deps = orgFromFile.getDepartments();
            for (Department dep : deps) {
                System.out.println("Department: " + dep.getDeptName());
                for (Employee emp : dep.getEmployees()) {
                    System.out.println("Employee: " + emp.getEmpName());

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

