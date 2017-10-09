public class QueueTest{
	public static void main(String[] args) {
		MyQueue mq = new MyQueue();
		mq.enqueue(1);
		mq.enqueue(2);
		mq.enqueue(12);
		mq.enqueue(222);
		mq.enqueue(99);
		mq.enqueue(10);
		mq.enqueue(6);
		mq.enqueue(91);
		mq.enqueue(72);
		mq.dequeue();
		mq.dequeue();
		mq.dequeue();
		mq.dequeue();
		mq.dequeue();
		mq.dequeue();
		mq.dequeue();
		mq.dequeue();
		mq.dequeue();
		mq.showQueue();
	}
}