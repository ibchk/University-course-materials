import java.security.KeyStore;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;
public class AL05 {
    public DirectedGraph graph = new DirectedGraph();

    private class DirectedGraph {
        private Map<String, List<String>> graph = new HashMap<>();

        /**
         * Add undirected edge to the graph.
         *
         * @param one   one element of the edge
         * @param other the other element of edge
         */
        public void addEdge(String one, String other) {
            if (!graph.containsKey(one)) {
                List<String> edges = new ArrayList<>();
                edges.add(other);
                graph.put(one, edges);
            } else {
                if (!graph.get(one).contains(other)) {
                    graph.get(one).add(other);
                }
            }
        }

        /**
         * Return the graph.
         *
         * @return the internal graph structure.
         */
        public Map<String, List<String>> getGraph() {
            return graph;
        }

        /**
         * Perform breadth first search.
         *
         * @param start the vertex to start the search from
         * @param goal  the goal vertex to find
         * @return the number of vertices of the path from start to goal including start and goal: e.g.,
         * start = A, goal = C, path = A, B, C => 3, so: (3, [A, B, C]), the path itself as a list of strings.
         * NB! You can return null as path and only return the number of nodes
         * that connect the start and goal vertices for partial credit
         * (some tests only check for number of nodes)
         */
        public SimpleEntry<Integer, List<String>> breadthFirstSearch(String start, String goal) {
            List<String> visited = new LinkedList<>();
            List<String> notVisited = new LinkedList<>(graph.keySet());
            List<String> queue = new LinkedList<>();
            Map<String, String> closeFriends = new HashMap<>();
            queue.add(start);
            if (!notVisited.contains(goal)){
                notVisited.add(goal);
            }

            String previous = null;
            while (queue.size() > 0){
                String newFriend = queue.get(0);
                String current = newFriend;
                closeFriends.put(current, previous);
                queue.remove(newFriend);
                visited.add(newFriend);
                if (newFriend.equals(goal)){
                    List<String> friendLine = new LinkedList<>();
                    friendLine.add(newFriend);
                    while (newFriend != null){
                        if (closeFriends.get(newFriend) != null){
                            friendLine.add(closeFriends.get(newFriend));
                            newFriend = closeFriends.get(newFriend);
                        }else {
                            break;
                        }
                    }
                    Collections.reverse(friendLine);
                    return new SimpleEntry<>(friendLine.size(), friendLine);
                }
                for (String friend: graph.get(newFriend)){
                    if (notVisited.contains(friend)){
                        closeFriends.put(friend, current);
                        notVisited.remove(friend);
                        queue.add(friend);
                    }
                    previous = current;
                }
            }
            return null;
        }
    }


    /**
     * Use buildGraphAndFindLink to build a graph using the DirectedGraph class and then use its breadthFirstSearch to
     * return the links.
     *
     * @param friends the list of friends as pairs
     *                (e.g., [["Juhan", "Jaan"], ["Juhan", "Siiri"]] means that "Juhan" knows "Jaan" and "Siiri")
     * @param pair    the pair to be searched
     * @return the number of people that connect the searched pair including the pair itself (e.g., if pair is
     * = ["Mark", "Johanna"] and path is ["Mark", "Peter", "Siiri", "Johanna"], the number of people is 4) the list of people that connect
     * the searched pair (e.g., pair = ["Mark", "Sam"] => result = ["Mark", "Siiri", "Helen", "Peeter", "Sam"])
     */
    public SimpleEntry<Integer, List<String>> buildGraphAndFindLink(List<SimpleEntry<String, String>> friends,
                                                                     SimpleEntry<String, String> pair) {
        if (friends != null && pair != null){
            for (SimpleEntry<String, String> onePair: friends){
                graph.addEdge(onePair.getKey(), onePair.getValue());
            }
            return graph.breadthFirstSearch(pair.getKey(), pair.getValue());
        }
        return null;
    }
}