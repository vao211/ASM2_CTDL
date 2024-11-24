class Node {
    Student data;
    Node next;
    Node previous;

    public Node(Student data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}