
class Solution {

    int[] parent;

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        // Already connected: cycle detected
        if (rootA == rootB) {
            return false;
        }

        // Connect the two groups
        parent[rootA] = rootB;

        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;

        parent = new int[n + 1];

        // Initially, every node is its own parent
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {

            int a = edge[0];
            int b = edge[1];

            if (!union(a, b)) {
                return edge;
            }
        }

        return new int[0];
    }
}
