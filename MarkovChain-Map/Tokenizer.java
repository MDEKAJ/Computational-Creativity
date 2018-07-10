import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    public static List<String> tokenize(String s) {
        s = s.toLowerCase();

        // match lower case words or punctuation as tokens
        Matcher m = Pattern.compile("\\p{Lower}+|\\p{Punct}").matcher(s);
        List<String> tokens = new ArrayList<String>();
        while(m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }
}
