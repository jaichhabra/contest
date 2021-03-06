package template.primitve.generated.graph;

import java.util.List;
import java.util.ListIterator;

public class IntegerHLPPBeta implements IntegerMaximumFlow {
    private List<IntegerFlowEdge>[] g;
    private ListIterator<IntegerFlowEdge>[] iterators;
    private int[] heights;
    private int[] excess;
    private int vertexNumber;
    Node[] nodes;

    public IntegerHLPPBeta(int vertexNumber) {
        iterators = new ListIterator[vertexNumber];
        heights = new int[vertexNumber];
        excess = new int[vertexNumber];
        nodes = new Node[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            nodes[i] = new Node();
            nodes[i].val = i;
        }
    }

    private int sink;
    private int source;

    public int send(int flow) {
        init(flow);
        relabelToFront();
        return excess[sink];
    }

    private void relabel(int root) {
        int minHeight = 2 * vertexNumber;
        for (IntegerFlowEdge c : g[root]) {
            if (c.rev.flow > 0) {
                minHeight = Math.min(minHeight, heights[c.to]);
            }
        }
        heights[root] = minHeight + 1;
    }

    private void discharge(int root) {
        while (excess[root] > 0) {
            if (!iterators[root].hasNext()) {
                relabel(root);
                iterators[root] = g[root].listIterator();
                continue;
            }
            IntegerFlowEdge c = iterators[root].next();
            int remain;
            if ((remain = c.rev.flow) > 0 && heights[c.to] + 1 == heights[root]) {
                int sent = Math.min(excess[root], remain);
                excess[root] -= sent;
                excess[c.to] += sent;
                IntegerFlow.send(c, sent);
                if (excess[root] == 0) {
                    iterators[root].previous();
                    return;
                }
            }
        }
    }

    private void relabelToFront() {
        Node head = null;
        for (int i = 0; i < vertexNumber; i++) {
            if (i == source || i == sink) {
                continue;
            }
            nodes[i].next = head;
            head = nodes[i];
        }

        Node trace = head;
        Node pre = null;
        while (trace != null) {
            int i = trace.val;
            int oldHeight = heights[i];
            discharge(i);
            if (heights[i] != oldHeight) {
                if (pre != null) {
                    pre.next = trace.next;
                    trace.next = head;
                    head = trace;
                }
                trace = head;
            }
            pre = trace;
            trace = trace.next;
        }
    }

    private void init(int flow) {
        for (int i = 0; i < vertexNumber; i++) {
            nodes[i].next = null;
            heights[i] = 0;
            excess[i] = 0;
        }
        heights[source] = vertexNumber;
        int sent = 0;
        for (IntegerFlowEdge c : g[source]) {
            int newFlow = Math.min(c.rev.flow, flow - sent);
            sent += newFlow;
            IntegerFlow.send(c, newFlow);
            excess[source] -= newFlow;
            excess[c.to] += newFlow;
        }

        for (int i = 0; i < vertexNumber; i++) {
            iterators[i] = g[i].listIterator();
        }
    }

    private static class Node {
        Node next;
        int val;
    }

    @Override
    public int apply(List<IntegerFlowEdge>[] g, int s, int t, int send) {
        vertexNumber = g.length;
        this.g = g;
        this.source = s;
        this.sink = t;
        return send(send);
    }
}
