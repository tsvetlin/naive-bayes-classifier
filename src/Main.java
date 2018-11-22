import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        ArrayList<double[]> messageList = new ArrayList<>();
        ArrayList<Double> valueList = new ArrayList<>();
        ArrayList<double[]> testList = new ArrayList<>();
        HashMap<Double, Double> valueMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lineCount = 0;
        String s;
        try {
            while ((s = br.readLine()) != null) {
                lineCount++;
                if (lineCount <= 80000) {
                    if (!s.equals("")) {
                        messageList.add(Stream.of(s.split("\t"))
                                .mapToDouble(Double::parseDouble)
                                .toArray());
                    } else {
                        messageList.add(new double[0]);
                    }
                } else if (lineCount <= 160000) {
                    valueList.add(Double.parseDouble(s));
                } else {
                    if (!s.equals("")) {
                        testList.add(Stream.of(s.split("\t"))
                                .mapToDouble(Double::parseDouble)
                                .toArray());
                    } else {
                        testList.add(new double[0]);
                    }
                }
            }

            int counter = 0;
            for (double[] list : messageList) {
                for (double item : list) {
                    if (!valueMap.containsKey(item)) {
                        valueMap.put(item, 0.5);
                    } else {
                        if (valueList.get(counter) == 0.0) {
                            valueMap.put(item, Math.max(valueMap.get(item) - 0.01, 0.0));
                        } else {
                            valueMap.put(item, Math.min(valueMap.get(item) + 0.01, 1.0));
                        }
                    }
                }
                counter++;
            }

            for (double[] list : testList) {
                double average = 0.0;
                for (double item : list) {
                    if (valueMap.get(item) != null) {
                        average += valueMap.get(item);
                    } else {
                        average += 0.5;
                    }
                }
                average /= list.length;
                if (average > 0.5) {
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
