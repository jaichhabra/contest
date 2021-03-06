package graphs.flows;

import java.util.*;
import java.util.stream.Stream;

// https://cp-algorithms.com/graph/min_cost_flow.html in O(E * V + min(E * logV * FLOW, V^2 * FLOW))
// negative-cost edges are allowed
// negative-cost cycles are not allowed
public class MinCostFlowDijkstra {

    List<Edge>[] graph;

    public MinCostFlowDijkstra(int n) {
        graph = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
        pot = new long[n];
        dist = new long[n];
        finished = new boolean[n];
        curflow = new long[n];
        prevedge = new int[n];
        prevnode = new int[n];
    }

    static class Edge {
        int to, rev;
        long cap;
        long f;
        long cost;

        public long getCap() {
            return cap;
        }

        public long getF() {
            return f;
        }

        public long getCost() {
            return cost;
        }

        Edge(int to, int rev, long cap, long cost) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
            this.cost = cost;
        }
    }

    public Edge addEdge(int s, int t, long cap, long cost) {
        Edge ans = new Edge(t, graph[t].size(), cap, cost);
        graph[s].add(ans);
        graph[t].add(new Edge(s, graph[s].size() - 1, 0, -cost));
        return ans;
    }

    void bellmanFord(int s, long[] dist) {
        int n = graph.length;
        Arrays.fill(dist, (long) 1e18);
        dist[s] = 0;
        boolean[] inqueue = new boolean[n];
        int[] q = new int[n];
        int qt = 0;
        q[qt++] = s;
        for (int qh = 0; qh != qt; qh++) {
            int u = q[qh % n];
            inqueue[u] = false;
            for (int i = 0; i < graph[u].size(); i++) {
                Edge e = graph[u].get(i);
                if (e.cap <= e.f)
                    continue;
                int v = e.to;
                long ndist = dist[u] + e.cost;
                if (dist[v] > ndist) {
                    dist[v] = ndist;
                    if (!inqueue[v]) {
                        inqueue[v] = true;
                        q[qt++ % n] = v;
                    }
                }
            }
        }
    }

    void dijkstra(int s, int t) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.add((long) s);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        Arrays.fill(finished, false);
        curflow[s] = Integer.MAX_VALUE;
        while (!finished[t] && !q.isEmpty()) {
            long cur = q.remove();
            int u = (int) (cur & 0xFFFF_FFFFL);
            int priou = (int) (cur >>> 32);
            if (priou != dist[u])
                continue;
            finished[u] = true;
            for (int i = 0; i < graph[u].size(); i++) {
                Edge e = graph[u].get(i);
                if (e.f >= e.cap)
                    continue;
                int v = e.to;
                long nprio = dist[u] + e.cost + pot[u] - pot[v];
                if (dist[v] > nprio) {
                    dist[v] = nprio;
                    q.add(((long) nprio << 32) + v);
                    prevnode[v] = u;
                    prevedge[v] = i;
                    curflow[v] = Math.min(curflow[u], e.cap - e.f);
                }
            }
        }
    }

    void dijkstra2(int s, int t) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        int n = graph.length;
        Arrays.fill(finished, false);
        curflow[s] = Integer.MAX_VALUE;
        for (int i = 0; i < n && !finished[t]; i++) {
            int u = -1;
            for (int j = 0; j < n; j++)
                if (!finished[j] && (u == -1 || dist[u] > dist[j]))
                    u = j;
            if (dist[u] == Integer.MAX_VALUE)
                break;
            finished[u] = true;
            for (int k = 0; k < graph[u].size(); k++) {
                Edge e = graph[u].get(k);
                if (e.f >= e.cap)
                    continue;
                int v = e.to;
                long nprio = dist[u] + e.cost + pot[u] - pot[v];
                if (dist[v] > nprio) {
                    dist[v] = nprio;
                    prevnode[v] = u;
                    prevedge[v] = k;
                    curflow[v] = Math.min(curflow[u], e.cap - e.f);
                }
            }
        }
    }

    long[] pot;
    long[] dist;
    boolean[] finished;
    long[] curflow;
    int[] prevedge;
    int[] prevnode;

    public long[] minCostFlow(int s, int t, long maxf) {
        int n = graph.length;
        Arrays.fill(pot, 0);
        Arrays.fill(dist, 0);
        Arrays.fill(finished, false);
        Arrays.fill(curflow, 0);
        Arrays.fill(prevedge, 0);
        Arrays.fill(prevnode, 0);

        bellmanFord(s, pot); // this can be commented out if edges costs are non-negative
        int flow = 0;
        int flowCost = 0;
        while (flow < maxf) {
          dijkstra(s, t); // E*logV
          //  dijkstra2(s, t); // V^2
            if (dist[t] == Integer.MAX_VALUE)
                break;
            for (int i = 0; i < n; i++)
                if (finished[i])
                    pot[i] += dist[i] - dist[t];
            long df = Math.min(curflow[t], maxf - flow);
            flow += df;
            for (int v = t; v != s; v = prevnode[v]) {
                Edge e = graph[prevnode[v]].get(prevedge[v]);
                e.f += df;
                graph[v].get(e.rev).f -= df;
                flowCost += df * e.cost;
            }
        }
        return new long[]{flow, flowCost};
    }

    // Usage example
    public static void main(String[] args) {
        MinCostFlowDijkstra mcf = new MinCostFlowDijkstra(3);
        mcf.addEdge(0, 1, 3, 1);
        mcf.addEdge(0, 2, 2, 1);
        mcf.addEdge(1, 2, 2, 1);
        long[] res = mcf.minCostFlow(0, 2, Integer.MAX_VALUE);
        long flow = res[0];
        long flowCost = res[1];
        System.out.println(4 == flow);
        System.out.println(6 == flowCost);
    }
}
