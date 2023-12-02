package utils.knapsack;

public class Knapsack {
    public static int mochila01(int capacidadMochila, Articulo[] objetos) {
        int n = objetos.length;
        int[][] dp = new int[n + 1][capacidadMochila + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacidadMochila; w++) {
                if (objetos[i - 1].peso <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], objetos[i - 1].valor + dp[i - 1][w - objetos[i - 1].peso]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacidadMochila];
    }

}
