import smile.anomaly.IsolationForest;
import java.io.BufferedReader;
import java.io.FileReader;

public class AnomalyDetector {

    public static void main(String[] args) {

  double[][] data = new double[12][4];

try {
    BufferedReader br = new BufferedReader(
        new FileReader("server_data.csv")
    );

    br.readLine();

    for (int i = 0; i < data.length; i++) {
        String[] values = br.readLine().split(",");

        data[i][0] = Double.parseDouble(values[0]);
        data[i][1] = Double.parseDouble(values[1]);
        data[i][2] = Double.parseDouble(values[2]);
        data[i][3] = Double.parseDouble(values[3]);
    }

    br.close();

} catch (Exception e) {
    System.out.println("Error reading CSV: " + e.getMessage());
    return;
}

        IsolationForest model = IsolationForest.fit(data);

       for (int i = 0; i < data.length; i++) {

    double score = model.score(data[i]);

    System.out.println("-----------------------------");
    System.out.println("Server Data " + (i + 1));
    System.out.println("CPU      : " + data[i][0] + "%");
    System.out.println("Memory   : " + data[i][1] + "%");
    System.out.println("Disk     : " + data[i][2] + "%");
    System.out.println("Network  : " + data[i][3] + "%");
    System.out.println("AI Score : " + score);

    if (score > 0.5) {
        System.out.println("Status   : NORMAL");
    } else {
        System.out.println("Status   : ANOMALY DETECTED");
        System.out.println("ALERT    : Server requires immediate attention!");
    }
}
    }
}