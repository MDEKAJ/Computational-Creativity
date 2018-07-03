import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Markov
{
  public static void main(String[] args)
    throws java.io.IOException, java.io.FileNotFoundException
  {
    //String filename = "pg2701.txt";
    String filename = "testFile.txt";
    String content = new String(Files.readAllBytes(Paths.get(filename)));
    String[] words = content.replaceAll("[^a-zA-Z ]", "")
      .toLowerCase().split("\\s+");

    int wc = words.length;
    System.out.println("Word count is "+wc);

    int distinctiveWC = 0;
    Map<String, Integer> index = new HashMap<>();
    Map<Integer, String> reverseIndex = new HashMap<>();
    for (String w: words)
      {
	if (!index.containsKey(w))
	  {
	    index.put(w,distinctiveWC);
	    reverseIndex.put(distinctiveWC,w);
	    distinctiveWC++;
	  }
      }
    System.out.println("Distinctive WC is "+distinctiveWC);

    /** initialise the count **/
    int[][] count = new int[distinctiveWC][distinctiveWC];
    for (int i=0;i<distinctiveWC;i++)
      {
	for (int j=0;j<distinctiveWC;j++)
	  {
	    count[i][j] = 0;
	  }
      }

    /** fill the count array **/
    for (int i=0;i<words.length-1;i++)
      {
	String first = words[i];
	String second = words[i+1];
	count[index.get(first)][index.get(second)]++;
      }

    /** test print **/
    for (int i=0;i<distinctiveWC;i++)
      {
	for (int j=0;j<distinctiveWC;j++)
	  {
	    System.out.print(count[i][j]+" ");
	  }
	System.out.println();
      }

    /** count the total words per line **/
    int[] total = new int[distinctiveWC];
    for (int i=0;i<distinctiveWC;i++)
      {
	total[i] = 0;
	for (int j=0;j<distinctiveWC;j++)
	  {
	    total[i] += count[i][j];
	  }
      }

    /** make a cumulative probability table **/
    double[][] prob = new double[distinctiveWC][distinctiveWC+1];
    double[] cumulativeProb = new double[distinctiveWC];
    for (int i=0;i<distinctiveWC;i++)
      {
	prob[i][0] = 0.0;
	cumulativeProb[i] = 0.0;
	for (int j=0;j<distinctiveWC;j++)
	  {
	    prob[i][j+1] = cumulativeProb[i]
	      +(double)count[i][j]/(double)total[i];
	    cumulativeProb[i] = prob[i][j+1];
	  }
      }

    /** print for testing **/

    for (String w: index.keySet())
      {
	System.out.println(w+" "+index.get(w));
      }
    for (int i=0;i<distinctiveWC;i++)
      {
	for (int j=0;j<=distinctiveWC;j++)
	  {
	    System.out.printf("%.2f  ", prob[i][j]);
	  }
	System.out.println();
      }

    /** create a new sentence **/

    int len = 25;
    String[] newWords = new String[len];
    newWords[0] = "the";
    for (int nw=1;nw<len;nw++)
      {
	System.out.println("New words: "+newWords[nw-1]);
	int row = index.get(newWords[nw-1]);
	double rnd = Math.random();
	int wordIndex = -999;
	for (int j=1;j<=distinctiveWC;j++)
	  {
	    System.out.printf("row is %d and prob is %.2f %n",row,rnd);
	    if ( (prob[row][j-1]<=rnd) && (prob[row][j]>rnd) )
		{
		  wordIndex = j-1;
		  break;
		}
	  }
	newWords[nw] = reverseIndex.get(wordIndex);
      }

    /** print out the final "sentence" **/
    for (int nw=0;nw<len;nw++)
      {
	System.out.print(newWords[nw]+" ");
      }
    System.out.println();
  }
}
