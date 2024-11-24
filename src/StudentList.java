class StudentList {// base on doubly linked list
    Node head;
    Node tail;

    // Insert method
    public void add(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        Student.count++;
    }

    // Traverse Method
    public void printList() {
        Node current = head;
        if (current == null) {
            System.out.println("No students available.");
            return;
        }

        while (current != null) {
            Student student = current.data;
            String string = "\nStudent name: " + student.getName() + "\n" +
                    "Student code: " + student.getCode() + "\n" +
                    "Point: " + student.getPoint() + "\n" +
                    "Student mark: " + student.getRank() + "\n";
            System.out.println(string);
            current = current.next;
        }
    }

    // Edit method
    public void edit(String code, Student newStudent) {
        Node current = head;
        while (current != null) {
            if (current.data.getCode().equals(code)) {
                current.data = newStudent;
                return;
            }
            current = current.next;
        }
        System.out.println("Student not found for editing.");
    }

    // Delete method
    public void remove(String code) {
        Node current = head;
        while (current != null) {
            if (current.data.getCode().equals(code)) {
                if (current.previous != null) {
                    current.previous.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.previous = current.previous;
                } else {
                    tail = current.previous;
                }
                Student.count--;
                return;
            }
            current = current.next;
        }
        System.out.println("Student not found.");
    }

    // search and check method
    public Student search(String code) {
        Node current = head;
        while (current != null) {
            if (current.data.getCode().equals(code)) {
                return current.data;
            }
            current = current.next;
        }
        return null; // ko thấy
    }
}