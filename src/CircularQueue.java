public class CircularQueue<T>{
    private int maxSize;
    private int front;
    private int rear;
    private Object[] queue;

    public CircularQueue(int size) {
        maxSize = size + 1;
        queue = new Object[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isEmpty() {return front == rear;}

    public boolean isFull() {return (rear + 1) % maxSize == front;}

    public void enqueue(T item) {
        if (!isFull()) {
            queue[rear] = item;
            rear = (rear + 1) % maxSize;
        }
    }

    public T dequeue() {
        if (!isEmpty()) {
            T item = (T) queue[front];
            front = (front + 1) % maxSize;
            return item;
        } else {
            return null; // Return null to indicate an empty queue
        }
    }

    public T peek() {
        if (isEmpty()) {
            return null; // Return null to indicate an empty queue
        } else {
            return (T) queue[front];
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            int i = front;
            while (i != rear) {
                System.out.println(queue[i]);
                i = (i + 1) % maxSize;
            }
            System.out.println();
        }
    }

    public int size(){return (maxSize + (rear - front)) % maxSize;}

    public T serve(){
        if(!isEmpty()){
            T servedItem = dequeue();
            enqueue(servedItem);
            return servedItem;
        }
        return null;
    }
}