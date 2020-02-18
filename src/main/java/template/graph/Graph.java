package template.graph;

import template.primitve.generated.datastructure.IntegerDeque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    public static <T extends DirectedEdge> void bfs(List<T>[] g, int s, int[] dist, int inf, IntegerDeque deque) {
        Arrays.fill(dist, inf);
        dist[s] = 0;
        deque.clear();
        deque.addLast(s);
        while (!deque.isEmpty()) {
            int head = deque.removeFirst();
            for (T e : g[head]) {
                if (dist[e.to] == inf) {
                    dist[e.to] = dist[head] + 1;
                    deque.addLast(e.to);
                }
            }
        }
    }

    public static void addEdge(List<DirectedEdge>[] g, int s, int t) {
        g[s].add(new DirectedEdge(t));
    }

    public static void addUndirectedEdge(List<UndirectedEdge>[] g, int s, int t) {
        UndirectedEdge toT = new UndirectedEdge(t);
        UndirectedEdge toS = new UndirectedEdge(s);
        toT.rev = toS;
        toS.rev = toT;
        g[s].add(toT);
        g[t].add(toS);
    }

    public static List<DirectedEdge>[] createDirectedGraph(int n) {
        List<DirectedEdge>[] ans = new List[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new ArrayList<>();
        }
        return ans;
    }

    public static List<UndirectedEdge>[] createUndirectedGraph(int n) {
        List<UndirectedEdge>[] ans = new List[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new ArrayList<>();
        }
        return ans;
    }
}