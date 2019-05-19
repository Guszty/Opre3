import java.util.ArrayList;

public class Task {
	public String name;
	public ArrayList<Instructions> instruct = new ArrayList<Instructions>();
	public ArrayList<String> resources = new ArrayList<String>();
	public Task (String n, ArrayList<String> r) {
		name = n;
		for (int i = 0; i < r.size(); i++) {
			String t = r.get(i).substring(0,1);
			String rn = r.get(i).substring(1);
			instruct.add(new Instructions(t, rn));
		}
	}
	public Task (ArrayList<Instructions> r, String n) {
		name = n;
		for (int i = 0; i < r.size(); i++) {
			instruct.set(i, r.get(i));
		}
	}
	public boolean contain(String s) {
		for (int i = 0; i < resources.size(); i++) {
			if (s == resources.get(i))
				return true;
		}
		return false;
	}
	public void removeElement() {
		instruct.remove(0);
	}
}
