import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Markov<E> {
    // we'll need a random number generator
    Random random;

    // this map will contain our Markov model
    // Effectively it's a map from n-grams -> probability tables
    // n-grams have type: List<E> - they're lists of E
    // our probability tables have type: Map<E, Integer>
    // so overall the model's type is:
    Map<List<E>, Map<E, Integer>> model;

    /**
     * Constructs a new Markov model from sequence with n-gram size of n.
     *
     * @param n         the size of the ngrams used by this model
     * @param sequence  the source sequence used to generate our model
     */
    public Markov(int n, List<E> sequence) {
        this(new Random(), n, sequence);
    }

    /**
     * Constructs a new Markov model from sequence with n-gram size of n.
     * A Random number generator can be specified reproducing sequences
     *
     * @param randomGen a random number generator used for navigating the model
     * @param n         the size of the n-grams used by this model
     * @param sequence  the source sequence used to generate our model
     */
    public Markov(Random randomGen, int n, List<E> sequence) {
        random = randomGen;
        model = new HashMap<List<E>, Map<E, Integer>>();

        // this is where we build the model - we iterate over the sequence
        for(int i = 0; i < sequence.size() - n; i++) {
            // grab an n-gram,
            List<E> nGram = sequence.subList(i, i + n - 1);

            // then look up the probability table for the current n-gram
            Map<E, Integer> probabilities = model.get(nGram);
            if(probabilities == null) {
                probabilities = new HashMap<E, Integer>();
            }

            // grab the item following our n-gram
            E nextItem = sequence.get(i + n - 1);

            // then look up and increment the count of times the nextItem has
            // followed the current n-gram so far
            Integer count = probabilities.get(nextItem);
            if(count == null) { count = 0; }
            probabilities.put(nextItem, ++count);

            // stick the probabilities/counts in the model
            model.put(nGram, probabilities);
        }
    }

    /**
     * Given an n-gram return a possible next item
     *
     *
     * @param nGram an nGram
     * @returns an item from the model
     */
    public E nextItem(List<E> nGram) {
        // Really simple - it's just a weighted choose on the probability table we
        // stored for each n-gram
        return weightedChoose(model.get(nGram));
    }

    /**
     * Creates a new n-gram from the passed in n-gram and a possible next item
     *
     * @param nGram an nGram
     * @returns an nGram
     */
    public List<E> nextNGram(List<E> nGram) {
        List<E> next = new ArrayList<E>(nGram);
        next.remove(0);            // remove first item
        next.add(nextItem(nGram)); // add new item to end
        return next;
    }

    /**
     * Produces a sequence using the markov model
     *
     * @param n the length of the sequence
     * @param nGram the nGram to start the sequence with
     * @returns sequence of objects from markov model
     */
    public List<E> sequence(int n, List<E> nGram) {
        // we just call nextNGram(..) over and over again adding each item to a list
        List<E> seq = new ArrayList<E>(nGram);
        List<E> next = nGram;
        for(int i = nGram.size(); i < n; i++) {
            next = nextNGram(next);
            seq.add(next.get(next.size() - 1));
        }

        return seq;
    }

    /**
     * Randomly selects an item from a map with the weights specified as values
     *
     * Google weighted choose if you don't know the algorithm! Super useful.
     * (btw. works just as well with Double etc just Ints in this context...)
     *
     * @probabilites a map of from values to there relative probabilities
     *               e.g. with a map like {"cat":1, "dog":1, "monkey":10}
     *               monkey will be returned 10 times as many times as cat or dog
     * @returns a value from the probabilities map
     */
    private E weightedChoose(Map<E, Integer> probabilities) {
        // whizz across the list of probabilities keeping running sum of them
        int runningSum = 0;
        ArrayList<Integer> summedWeights = new ArrayList<Integer>();
        for(int p : probabilities.values()) {
            runningSum += p;
            summedWeights.add(runningSum);
        }

        // choose a number 0 and the sum
        int threshold = random.nextInt(runningSum);

        // find the index of the item to first exceed this number
        int i = 0;
        while(i < probabilities.size() - 1 &&
              threshold < summedWeights.get(i)) {
            i++;
        }

        // pull the item out of the map using the index we now have
        ArrayList<E> items = new ArrayList<E>(probabilities.keySet());
        return items.get(i);
    }

    /**
     * Helper function that returns all of the n-grams used in the model
     * Useful for finding an n-gram to start a sequence with
     *
     * @returns List of n-grams
     */
    public List<List<E>> getNGrams() {
        return new ArrayList<List<E>>(model.keySet());
    }

    /**
     * We'll just use HashMap's .toString()
     * You'll just have to squint
     */
    public String toString() {
        return model.toString();
    }
}
