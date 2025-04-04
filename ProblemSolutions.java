
/******************************************************************
 *
 *   Dorsa Valipourkarimi / COMP 272 002
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     * If x == y, both boulders are destroyed, and
     * If x != y, the boulder of weight x is destroyed, and the boulder of
     * weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

    public static int lastBoulder(int[] boulders) {
        // creates a max heap to always access the two heaviest boulder
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // adds all boulders to the maxheap
        for (int boulder : boulders) {
            pq.add(boulder);
        }

        // keeps smashing the two heaviest boulders until one or none remain
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();

            // if the weights are not equal, the remaining weight goes back into the heap
            if (first != second) {
                pq.add(first - second);
            }
        }

        // returns the last remaining boulder, or 0 if none are left
        if (pq.isEmpty()) {
            return 0;
        } else {
            return pq.peek();
        }
    }

    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse",
     * "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param input an ArrayList<String>
     * @return an ArrayList<String> containing only unique strings that appear
     *         more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {

        // creates a map to keep track of how many times each string appears
        HashMap<String, Integer> counts = new HashMap<>();

        // creates a list to store strings that appear more than once
        ArrayList<String> result = new ArrayList<>();

        // loops through each string in the input list
        for (String word : input) {
            // if the string is already in the map, increment its count
            if (counts.containsKey(word)) {
                counts.put(word, counts.get(word) + 1);
            }
            // if the string is not in the map, add it with a count of 1
            else {
                counts.put(word, 1);
            }
        }

        // loops through the keys in the map
        for (String word : counts.keySet()) {
            // if the string’s count is greater than 1, it is a duplicate
            if (counts.get(word) > 1) {
                // adds the duplicate string to the result list
                result.add(word);
            }
        }

        // sorts the result list in alphabetical order
        Collections.sort(result);

        // returns the final list of sorted duplicates
        return result;
    }

    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input Array of integers
     * @param k     The sum to find pairs for
     * 
     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *         of strings needs to be ordered both within a pair, and
     *         between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *         A string is a pair in the format "(a, b)", where a and b are
     *         ordered lowest to highest, e.g., if a pair was the numbers
     *         6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *         The ordering of strings of pairs should be sorted in lowest to
     *         highest pairs. E.g., if the following two string pairs within
     *         the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *         ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *         HINT: Considering using any Java Collection Framework ADT that we
     *         have used
     *         to date, though HashSet. Consider using Java's "Collections.sort()"
     *         for final
     *         sort of ArrayList before returning so consistent answer. Utilize
     *         Oracle's
     *         Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {
        // stores numbers that have already been seen
        HashSet<Integer> seen = new HashSet<>();

        // stores formatted string pairs to avoid duplicates
        HashSet<String> uniquePairs = new HashSet<>();

        // loops through each number in the input array
        for (int i = 0; i < input.length; i++) {
            int num = input[i];
            int complement = k - num;

            // checks if the complement has already been seen
            if (seen.contains(complement)) {
                // determines the lower and higher values for formatting
                int low = Math.min(num, complement);
                int high = Math.max(num, complement);

                // formats the pair as a string and adds to the set
                String pairStr = "(" + low + ", " + high + ")";
                uniquePairs.add(pairStr);
            }

            // adds the current number to the set of seen values
            seen.add(num);
        }

        // converts the set of unique pairs into a list
        ArrayList<String> result = new ArrayList<>(uniquePairs);

        // sorts the list of pairs in ascending order
        Collections.sort(result);

        // returns the sorted list of string pairs
        return result;
    }
}