import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static StudentList studentList = new StudentList();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print(
                        "\n0: View \n1: Add Student \n2: Remove Student \n3: Edit Student \n4: Search Student \n5: Sort Student \n6: Exit \n");
                System.out.print("\nEnter: ");
                int input = getValidInt();
                switch (input) {
                    case 6 -> {
                        System.out.println("Exit program.");
                        return;
                    }
                    case 0 -> {
                        printStudent();
                        System.out.println("Number of Students: " + Student.count);
                    }
                    case 1 -> {
                        System.out.print("\nNumber of students: ");
                        int num = getValidPostInt();
                        for (int i = 0; i < num; i++) {
                            addStudent();
                        }
                    }
                    case 2 -> {
                        System.out.print("Number of students to remove: ");
                        int num = getValidPostInt();
                        if (num <= Student.count) {
                            for (int i = 0; i < num; i++) {
                                removeStudent();
                            }
                        } else {
                            System.out.println("Please enter a smaller number!");
                        }
                    }
                    case 3 -> editStudent();
                    case 4 -> searchStudent();
                    case 5 -> {
                        System.out.print("Sort Descending(1) / Sort Ascending(2) ?: ");
                        int select = getValidInt();
                        switch (select) {
                            case 1 -> sortDescending();
                            case 2 -> sortAscending();
                            default -> System.out.println("Invalid selection. Please enter 1 or 2.");
                        }
                    }
                    default -> System.out.println("Please enter a number between 0 and 6.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                return 6;
            }
        }
    }

    private static int getValidPostInt() {
        while (true) {
            int value = getValidInt();
            if (value > 0) {
                return value;
            } else {
                System.out.println("Number must be > 0.");
                return 0;
            }
        }
    }

    private static double getValidDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double number = Double.parseDouble(scanner.nextLine());
                if (number < 0) {
                    System.out.println("Point must be non-negative.");
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for point. Please enter a valid number.");
            }
        }
    }

    private static void addStudent() {
        Student student = new Student();
        System.out.print("\nEnter name: ");
        student.setName(scanner.nextLine());

        String code;
        while (true) {
            System.out.print("Enter code: ");
            code = scanner.nextLine();
            if (!checkDuplicate(code)) {
                break;
            }
            System.out.println("Student code already exists. Please enter a different code.");
        }
        student.setCode(code);
        student.setPoint(getValidDoubleInput("Enter point: "));
        studentList.add(student);
    }

    private static void printStudent() {
        studentList.printList();
    }

    private static boolean checkDuplicate(String code) {
        Node current = studentList.head;
        while (current != null) {
            if (current.data.getCode().equals(code)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private static void removeStudent() {
        System.out.print("Enter the code of the student to remove: ");
        String code = scanner.nextLine();
        studentList.remove(code);
    }

    private static void editStudent() {
        System.out.print("Enter the code of the student to edit: ");
        String studentCode = scanner.nextLine();
        Student foundStudent = studentList.search(studentCode);

        if (foundStudent == null) {
            System.out.println("Student with code " + studentCode + " not found.");
            return;
        }

        System.out.print("Enter new Student Code (leave empty to keep current): ");
        String newCode = scanner.nextLine();
        if (!newCode.isEmpty() && !newCode.equals(foundStudent.getCode())) {
            if (checkDuplicate(newCode)) {
                System.out.println("Student already exists with the code: " + newCode);
            } else {
                foundStudent.setCode(newCode);
            }
        }

        System.out.print("Enter new Student Name (leave empty to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            foundStudent.setName(newName);
        }

        System.out.print("Enter new Point (leave empty to keep current): ");
        String pointInput = scanner.nextLine();
        if (!pointInput.isEmpty()) {
            try {
                double newPoint = Double.parseDouble(pointInput);
                foundStudent.setPoint(newPoint);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for point. Keeping current point.");
            }
        }
    }

    private static void searchStudent() {
        System.out.print("Enter the code of the student to search: ");
        String code = scanner.nextLine();
        Student foundStudent = studentList.search(code);
        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent.getName() +
                    ", Code: " + foundStudent.getCode() +
                    ", Point: " + foundStudent.getPoint() +
                    ", Rank: " + foundStudent.getRank());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void sortAscending() {
        selectionSort(true);
        studentList.printList();
    }

    private static void sortDescending() {
        selectionSort(false);
        studentList.printList();
    }

    // thuật toán mới selection sort
    private static void selectionSort(boolean ascending) {
        System.out.println("   Selection Sort");
        if (studentList.head == null || studentList.head.next == null)
            return;
        Node current = studentList.head;
        while (current != null) {
            Node minNode = current;
            Node nextNode = current.next;
            while (nextNode != null) {
                if ((ascending && nextNode.data.getPoint() < minNode.data.getPoint()) ||
                        (!ascending && nextNode.data.getPoint() > minNode.data.getPoint())) {
                    minNode = nextNode;
                }
                nextNode = nextNode.next;
            }

            if (minNode.data != current.data) {
                Student temp = current.data;
                current.data = minNode.data;
                minNode.data = temp;
            }
            current = current.next;
        }
    }

    // thuật toán cũ bubble sort
    @SuppressWarnings("unused")
    private static void bubbleSort(boolean ascending) {
        System.out.println(" Bubble Sort");
        if (studentList.head == null)
            return;
        else if (Student.count == 1) {
            studentList.printList();
        }
        boolean swap;
        do {
            swap = false;
            Node current = studentList.head;
            while (current.next != null) {
                if ((ascending && current.data.getPoint() > current.next.data.getPoint()) ||
                        (!ascending && current.data.getPoint() < current.next.data.getPoint())) {
                    Student temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swap = true;
                }
                current = current.next;
            }
        } while (swap);
    }
}