import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        StationList stationList = new StationList();
        stationList.addStation(new RadioStation(89));
        stationList.addStation(new RadioStation(101));
        stationList.addStation(new RadioStation(102));
        stationList.addStation(new RadioStation(103));

        Iterator<RadioStation> iterator = stationList.iterator();
        while (iterator.hasNext()) {
            RadioStation station = iterator.next();
            System.out.println(station.getFrequency());
        }
        stationList.removeStation(new RadioStation(89));
        // How can i reiterate after removing one of the radio stations ?
    }
}