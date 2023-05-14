package halfbind.UNISTBusMate.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.opencsv.CSVReader;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;

public class DataManager {
    // @Autowired
    // private final BusRepository busRepository;

    public static List<Bus> getBusData() throws Exception {
        List<Bus> result = new ArrayList<>();

        Resource resource = new ClassPathResource("static/initial-data.csv");
        InputStream input = resource.getInputStream();
        CSVReader csvReader = new CSVReader(new InputStreamReader(input));

        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            try {
                Bus bus = new Bus();
                List<DestinationInfo> destinationInfos = new ArrayList<>();

                String departureTime = nextLine[0];
                bus.setDepartureTime(departureTime);
                bus.setRouteNumber(nextLine[1]);
                bus.setRouteDirection(nextLine[2]);

                List<String> destinationNames = List.of(nextLine[3].split("\\|"));
                for (String destinationName : destinationNames) {
                    Destination destination = Destination.get(destinationName);
                    if (destination == null) {
                        continue;
                    }
                    DestinationInfo destinationInfo = new DestinationInfo(destination, departureTime);
                    destinationInfo.setBus(bus);
                    destinationInfos.add(destinationInfo);
                }
                bus.setDestinationInfos(destinationInfos);
                result.add(bus);
            } catch (Exception e) {
                continue;
            }
        }

        return result;
    }
}
