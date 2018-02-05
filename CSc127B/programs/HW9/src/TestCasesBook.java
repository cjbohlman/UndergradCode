import java.util.ArrayList;

public class TestCasesBook {
	public static void main(String[] args) {
		System.out.println("start");
		ALinkedList<String> list = new ALinkedList<String>();
		list.insertInOrder("dog");
		list.insertInOrder("cat");
		list.insertInOrder("rabbit");
		System.out.println(list.toString());
		System.out.println(list.find("dog"));
		list.remove("dog");
		System.out.println(list.toString());
		System.out.println("end");
		ArrayList newList = new ArrayList();
		newList = list.getElements();
	}

}
