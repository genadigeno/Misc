package geno.micros.task1;

import org.springframework.boot.CommandLineRunner;

public class TollProcessingTask implements CommandLineRunner {

    public TollProcessingTask() {}

    @Override
    public void run(String... args) throws Exception {
        //oarameters stationId, license, timestamp
        if (args != null) {
            System.out.println("parameters length is: " + args.length);
            String stationId    = args[0];
            String licensePlate = args[1];
            String timestamp    = args[2];
            System.out.print("stationId: " + stationId);System.out.print(", licensePlate: " + licensePlate);
            System.out.println(", timestamp: " + timestamp);
        }
    }
}
