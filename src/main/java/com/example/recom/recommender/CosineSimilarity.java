package com.example.recom.recommender;

public class CosineSimilarity {
    public static double calculate(double[] vectorA, double[] vectorB) {
        int minLength = Math.min(vectorA.length, vectorB.length);

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < minLength; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
