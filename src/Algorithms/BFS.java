package Algorithms;

import labyrinth.Node;

import java.util.ArrayList;
import java.util.Collections;

public class BFS {

    private Node node = new Node();
    private Node[][] graph;

    public void searchExit(char[][] labirynth) {
        graph = node.createGraph(labirynth);

        boolean[] visited = new boolean[graph.length * graph[0].length];

        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        ArrayList<Node> BFS = new ArrayList();
        ArrayList<Node> exit = new ArrayList();

        Node start = node.findStart(graph);

        BFS.add(start);
        visited[start.getNodeValue() - 1] = true;

        long startTime = System.nanoTime();
        //tworzenie BFSa do znalezienia wierzcholka bedacego wyjsciem
        while (BFS.size() > 0) {
            Node deletedNode = BFS.remove(0);
            //istnieje lewy
            if (deletedNode.getNodeLeft() != null)
                if (!visited[deletedNode.getNodeLeft().getNodeValue() - 1]) {
                    BFS.add(deletedNode.getNodeLeft());
                    visited[deletedNode.getNodeLeft().getNodeValue() - 1] = true;
                    if (deletedNode.getNodeLeft().isExit()) {
                        exit.add(deletedNode);
                        exit.add(deletedNode.getNodeLeft());
                        break;
                    }
                }
            //istnieje prawy
            if (deletedNode.getNodeRight() != null)
                if (!visited[deletedNode.getNodeRight().getNodeValue() - 1]) {
                    BFS.add(deletedNode.getNodeRight());
                    visited[deletedNode.getNodeRight().getNodeValue() - 1] = true;
                    if (deletedNode.getNodeRight().isExit()) {
                        exit.add(deletedNode);
                        exit.add(deletedNode.getNodeRight());
                        break;
                    }
                }
            //istnieje gorny
            if (deletedNode.getNodeUp() != null)
                if (!visited[deletedNode.getNodeUp().getNodeValue() - 1]) {
                    BFS.add(deletedNode.getNodeUp());
                    visited[deletedNode.getNodeUp().getNodeValue() - 1] = true;
                    if (deletedNode.getNodeUp().isExit()) {
                        exit.add(deletedNode);
                        exit.add(deletedNode.getNodeUp());
                        break;
                    }
                }
            //istnieje dolny
            if (deletedNode.getNodeDown() != null)
                if (!visited[deletedNode.getNodeDown().getNodeValue() - 1]) {
                    BFS.add(deletedNode.getNodeDown());
                    visited[deletedNode.getNodeDown().getNodeValue() - 1] = true;
                    if (deletedNode.getNodeDown().isExit()) {
                        exit.add(deletedNode);
                        exit.add(deletedNode.getNodeDown());
                        break;
                    }
                }
            exit.add(deletedNode);
        }

        Collections.reverse(exit);
        System.out.println();
        Node tmp = exit.remove(0);

        while (tmp != start) {
            if (tmp.isConnected(exit.get(0))) {
                System.out.print(tmp.getNodeValue() + " -> ");
                tmp = exit.remove(0);
            } else
                exit.remove(0);
        }
        System.out.println(start.getNodeValue());
        long endTime = System.nanoTime();
        System.out.println("Time in nanos: " + (endTime - startTime));
    }
}
