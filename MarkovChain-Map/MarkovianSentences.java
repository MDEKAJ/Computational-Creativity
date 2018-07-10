import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MarkovianSentences {
    public static void main(String[] args) {
        // Get the words we are going to put in our Markov model
        String string = "";
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get("/users/metdekaj/desktop/Testing/testFile.txt"));
            string = new String(fileBytes);

            // note you could just as easily in your tweets here
            // string = Twitter.getTweetsForUser("realDonaldTrump");
        } catch(IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        // chop our big string up in to separate words, punctuation etc
        List<String> words = Tokenizer.tokenize(string);

        // This makes a Markov model with trigrams in of our words
        // - change 3 to vary the size of the ngrams used.
        // - note that this class works with any List of Objects, not just Lists
        //   of Strings
        Markov<String> model = new Markov<String>(4, words);

        // get a list of all the ngrams from our model and randomly choose one
        // to start with
        List<List<String>> ngrams = model.getNGrams();
        Random r = new Random();
        List<String> startNGram = ngrams.get(r.nextInt(ngrams.size()));

        // print some 40 word sequences, each with there own random start point
        System.out.println("--- Example sentences ---");

        List<String> sequence = model.sequence(40, startNGram);
        System.out.println(String.join(" ", sequence));

        startNGram = ngrams.get(r.nextInt(ngrams.size()));
        sequence = model.sequence(40, startNGram);
        System.out.println(String.join(" ", sequence));

        startNGram = ngrams.get(r.nextInt(ngrams.size()));
        sequence = model.sequence(40, startNGram);
        System.out.println(String.join(" ", sequence));

        System.out.println();

        // obviously can also ask for just one more ngram at a time
        System.out.println("--- One ngram at a time ---");

        List<String> ngram = model.nextNGram(startNGram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        ngram = model.nextNGram(ngram);
        System.out.println(ngram);
        
    }
}
