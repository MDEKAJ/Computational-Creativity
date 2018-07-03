

public class Table
{
  public static void main(String[] args)
    throws java.io.IOException, java.io.FileNotFoundException

  {
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
}
}
