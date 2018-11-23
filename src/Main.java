import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        ArrayList<int[]> messageList = new ArrayList<>();
        ArrayList<Integer> valueList = new ArrayList<>();
        ArrayList<int[]> testList = new ArrayList<>();
        HashMap<Integer, Integer> positiveList = new HashMap<>();
        HashMap<Integer, Integer> negativeList = new HashMap<>();
        HashMap<Integer, Integer> wordCounter = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lineCount = 0;
        String s;
        try {
            while ((s = br.readLine()) != null) {
                lineCount++;
                if (lineCount <= 80000) {
                    if (!s.equals("")) {
                        messageList.add(Stream.of(s.split("\t"))
                                .mapToInt(Integer::parseInt)
                                .toArray());
                    } else {
                        messageList.add(new int[0]);
                    }
                } else if (lineCount <= 160000) {
                    valueList.add(Integer.parseInt(s));
                } else {
                    if (!s.equals("")) {
                        testList.add(Stream.of(s.split("\t"))
                                .mapToInt(Integer::parseInt)
                                .toArray());
                    } else {
                        testList.add(new int[0]);
                    }
                }
            }

            int counter = 0;
            for (int[] list : messageList) {
                for (int item : list) {
                    if (valueList.get(counter) == 0) {
                        //negativeList put in item and occurrence++
                        negativeList.put(item, negativeList.containsKey(item) ? negativeList.get(item) + 1 : 1);
                    } else {
                        //positiveList put in item and occurrence++
                        positiveList.put(item, positiveList.containsKey(item) ? positiveList.get(item) + 1 : 1);
                    }
                    wordCounter.put(item, 1);
                }
                counter++;
            }

            for (int[] list : testList) {
                double positiveProbability = 1.0;
                double negativeProbability = 1.0;
                for (int item : list) {
                    double positiveWordProbability = positiveList.getOrDefault(item, 0);
                    positiveProbability *= ((positiveWordProbability + 1.0) / (positiveList.size() + wordCounter.size()));
                    double negativeWordProbability = negativeList.getOrDefault(item, 0);
                    negativeProbability *= ((negativeWordProbability + 1.0) / (negativeList.size() + wordCounter.size()));
                }
                if(positiveProbability > negativeProbability){
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
