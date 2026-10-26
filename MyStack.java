public class MyStack<E extends Comparable<? super E>> {

    private final MyDblLinkedList<E> list = new MyDblLinkedList<E>();

    //ADD ALL STACK FUNCTIONS
    // The tail of the lecture's doubly linked list is the top of the stack.

    // PRE: item has type E.
    // POST: adds item to the top and increases the size by one.
    public void push(E item) {
        list.addLast(item);
    }

    // PRE: none.
    // POST: removes and returns the top item; throws if the stack is empty.
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.removeLast();
    }

    // PRE: none.
    // POST: returns the top item without removing it; throws if empty.
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.getLast();
    }

    // PRE: none.
    // POST: returns the number of items without changing the stack.
    public int size() {
        return list.size();
    }

    // PRE: none.
    // POST: returns true if the stack contains no items.
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // PRE: none.
    // POST: removes all items from the stack.
    public void clear() {
        list.clear();
    }

    // PRE: none.
    // POST: returns the items from bottom to top without changing the stack.
    public String stackString() {
        if (isEmpty()) {
            return "[]";
        }
        return list.toString();
    }

}
