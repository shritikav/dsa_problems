
class Solution {

    int[] dsu;

    public int find(int x) {
        if (dsu[x] != x) {
            dsu[x] = find(dsu[x]);
        }
        return dsu[x];
    }

    public boolean union(int a, int b) {

        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;
        }

        dsu[rootB] = rootA;
        return true;
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {

        int n = edges.length;

        // Step 1: Detect whether a node has two parents
        int[] parent = new int[n + 1];

        int[] candidate1 = null;
        int[] candidate2 = null;

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            if (parent[v] == 0) {
                parent[v] = u;
            } 
            else {
                candidate1 = new int[]{parent[v], v};
                candidate2 = edge;
                break;
            }
        }

        // Step 2: Initialize DSU
        dsu = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dsu[i] = i;
        }

        // Step 3: Detect cycle
        for (int[] edge : edges) {

            // Skip the second incoming edge
            if (candidate2 != null &&
                edge == candidate2) {
                continue;
            }

            int u = edge[0];
            int v = edge[1];

            if (!union(u, v)) {

                // Case 1: Only cycle
                if (candidate1 == null) {
                    return edge;
                }

                // Case 3: Two parents + cycle
                return candidate1;
            }
        }

        // Case 2: Two parents, no cycle
        return candidate2;
    }
}
