import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Daria on 28.05.2016.
 */
interface Task {
    void execute();
    Set<Task> dependencies();
    int getNumber();
}


class MyTask implements Task {

    Set<Task> tasks;
    int number;

    MyTask(int number) {
        this.number = number;
        tasks = new HashSet<>();
    }

    @Override
    public void execute() {
        System.out.println("Execute task " + number);
    }

    @Override
    public Set<Task> dependencies() {
        return tasks;
    }

    @Override
    public int getNumber() {
        return number;
    }
}

class Scheduler {

    List<List<Integer>> vertex;
    List<Integer> res;
    int[] used;
    int n;

    Scheduler(List<Task> tasks) {
        n = tasks.size() + 1;
        used = new int[n];
        vertex = new ArrayList<>();
        res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            vertex.add(new ArrayList<>());
            used[i] = 0;
        }

        for (Task task: tasks) {
            int num = task.getNumber() + 1;
            for (Task dep : task.dependencies()) {
                vertex.get(dep.getNumber() + 1).add(num);
            }
            vertex.get(0).add(num);
        }
    }

    void dfs(int v) throws Exception {
        used[v] = 1;
        for (Integer to: vertex.get(v)) {
            if (used[to] == 1) {
                throw new Exception("Graph has a circle");
            }
            if (used[to] == 0) {
                dfs(to);
            }
        }
        used[v] = 2;
        if (v != 0) res.add(v);
        return;
    }

    List<Integer> schedule() throws Exception {
        try {
            dfs(0);
            for (int i = 0; i < res.size() / 2; i++) {
                int tmp = res.get(i);
                int pos = res.size() - i - 1;
                res.set(i, res.get(pos));
                res.set(pos, tmp);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("It is impossible. Schedule has a circle");
        }
    }
}


class ImmutableClass {

    public static class MetaProp {
        public int prop1;
        public int prop2;
    }

    private final int a;
    private String b;
    private final List metas;

    public ImmutableClass(int a, String b, List metas)
    {
        this.a = a;
        this.b = b;
        this.metas = Arrays.asList(metas.toArray());
    }

    public int getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public List getMetas() {
        return metas;
    }
}

public class Main {

    static String[] mySort(String[] lst) {
        String[] res = new String[lst.length];
        for (int i = 0; i < lst.length; i++) res[i] = new String(lst[i]);
        Arrays.sort(res, Collections.reverseOrder());
        System.err.println(Arrays.toString(res));
        return res;
    }

    public static void main(String[] args) {

        String str = "abc";
        List<String> lst = new ArrayList<>();
        lst.add("abc");
        ImmutableClass cl = new ImmutableClass(2, str, lst);
        System.err.println(cl.getA() + " " + cl.getB() + " " + Arrays.toString(cl.getMetas().toArray()));
        str = "sdf";
        ImmutableClass.MetaProp xx = new ImmutableClass.MetaProp();
        xx.prop1 = 2;
        xx.prop2 = 3;
        System.err.println(xx.prop1);
        xx.prop1 = 5;
        System.err.println(xx.prop1);
        lst.add("dsd");
        cl.getMetas().add("fsdfds");
        System.err.println(cl.getA() + " " + cl.getB() + " " + Arrays.toString(cl.getMetas().toArray()));
        String[] arr = {"cba", "dca", "bbbbaaa", "xy", "a"};
        String[] res1 = mySort(arr);
        System.err.println(Arrays.toString(arr));
        System.err.println(Arrays.toString(res1));

        List<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // count of tasks, numbers of tasks start with 1
        for (int i = 0; i < n; i++) {
            tasks.add(new MyTask(i));
        }
        for (int i = 0; i < n; i++) {
            int m = in.nextInt(); // count of dependencies of task with number i
            for (int j = 0; j < m; j++) {
                int num = in.nextInt() - 1;
                tasks.get(i).dependencies().add(tasks.get(num));
            }
        }
        Scheduler scheduler = new Scheduler(tasks);
        try {
            List<Integer> res = scheduler.schedule();
            for (int x: res) System.out.print(x + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
