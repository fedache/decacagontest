package com.afedare.decagontest.socks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SocksProblem {

    public static int noOfWashes(int noOfWashes, int[] cleanPile, int[] dirtyPile) {
        HashMap<Integer, Integer> frequencySock = new HashMap<>();
        for (int sockLeg : cleanPile) {
            frequencySock.put(sockLeg, frequencySock.getOrDefault(sockLeg, 0) + 1);
        }

        // Identify needed socks
        HashSet<Integer> socksNeeded = new HashSet<>();
        int totalPairs = 0;
        for (Map.Entry<Integer, Integer> entry : frequencySock.entrySet()) {
            int freq = entry.getValue();
            int sock = entry.getKey();

            int pairs = freq / 2;
            totalPairs += pairs;
            if (freq % 2 != 0) {
                socksNeeded.add(sock);
            }
        }

        // Pick 1 leg from dirty pile, if needed
        boolean[] dirtyPilePicked = new boolean[dirtyPile.length];
        noOfWashes = Math.min(noOfWashes, dirtyPile.length);
        int index = 0;
        while (noOfWashes - 1 >= 0 && index < dirtyPile.length) {
            int dirtyLeg = dirtyPile[index];
            boolean isNeeded = socksNeeded.contains(dirtyLeg);
            if (isNeeded) {
                dirtyPilePicked[index] = true;
                socksNeeded.remove(dirtyLeg);
                totalPairs++;
                noOfWashes--;
            }
            index++;
        }

        frequencySock = new HashMap<>();
        for (int i = 0; i < dirtyPile.length; i++) {
            int sockLeg = dirtyPile[i];
            if (dirtyPilePicked[i]) continue;
            frequencySock.put(sockLeg, frequencySock.getOrDefault(sockLeg, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : frequencySock.entrySet()) {
            int freq = entry.getValue();
            if (noOfWashes - 2 < 0) break;

            int pairs = freq / 2;
            while (noOfWashes - 2 >= 0 && pairs - 1 >= 0) {
                pairs = pairs - 1;
                noOfWashes = noOfWashes - 2;
                totalPairs++;
            }
        }

        return totalPairs;
    }
}
