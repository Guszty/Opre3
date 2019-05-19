import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<Task> tasks = new ArrayList<Task>(); 
	public static void main(String[] args) throws IOException {
		String line;
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		while ((!(line = buffer.readLine()).trim().equals(""))) {
			String[] parts = line.split(",");
			ArrayList<String> newtask = new ArrayList<String> ();
			for (int i = 1; i < parts.length; i++) {
				newtask.add(parts[i]);
			}
			Task task1 = new Task(parts[0], newtask);
			tasks.add(task1);
		}
		/*ArrayList<Task> tmptask = new ArrayList<Task>();
		for (int i = 0; i < tasks.size(); i++) {
			Task t1 = new Task(tasks.get(i).instruct, tasks.get(i).name);
			tmptask.set(i, t1);
		}*/
		while(empty(tasks)) {
			for (int i = 0; i < tasks.size(); i++){
				type(tasks.get(i).instruct.get(1), tasks.get(i), tasks);
			}
		}
		
	}
	
	public static boolean empty(ArrayList<Task> t1) {
		for (int i = 0; i < t1.size(); i++) {
			if (t1.get(i).instruct.size() > 0)
				return true;
		}
		return false;
	}
	
	public static void type(Instructions i1, Task t1, ArrayList<Task> tsks){
		if (i1.type.equals("-")) {
			if (!(t1.contain(i1.resource_name))) {
				String s = i1.resource_name;
				t1.resources.remove(s);
				t1.instruct.remove(i1);
			}
			else
				t1.instruct.remove(i1);
		}
		else {
			if (i1.type.equals("+")) {
				if (t1.resources.size() == 0) {
					t1.resources.add(i1.resource_name);
					t1.instruct.remove(i1);
				}
				else {
					Task task1 = isContain(i1.resource_name, tsks);
					if (task1 == t1) {
						t1.instruct.remove(i1);
						System.out.println(t1+","+find(t1, i1)+","+i1.resource_name);
					}
					if (task1 != null && (allocate(task1) == 0)) {
						t1.resources.add(i1.resource_name);
						t1.instruct.remove(i1);
					}
					else {
						if(isContain(allocate2(task1), tsks) == t1)
							t1.instruct.remove(i1);
					}
				}
			}
			
			else
				t1.instruct.remove(i1);
		}
	}
	public static Task isContain(String s1, ArrayList<Task> t1) {
		for (int i = 0; i < t1.size(); i++) {
			if(t1.get(i).contain(s1))
				return t1.get(i);
		}
		return null;
	}
	
	public static int allocate(Task t) {
		int al = 0;
		for (int i = 0; i < t.instruct.size(); i++) {
			if (t.instruct.get(i).type.equals("+"))
				al++;
		}
		return al;
	}
	
	public static int find(Task t, Instructions i1) {
		for (int i = 0; i < t.instruct.size(); i++) {
			if (t.instruct.get(i) == i1)
				return i;
		}
		return 0;
	}
	
	public static String allocate2(Task t) {
		for (int i = 0; i < t.instruct.size(); i++) {
			if (t.instruct.get(i).type.equals("+"))
				return t.instruct.get(i).resource_name;
		}
		return null;
	}
}
