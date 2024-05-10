import java.util.ArrayList;
import java.util.Iterator;

public class StationList implements Iterable<RadioStation> {
    private ArrayList<RadioStation> stations = new ArrayList<>();
    private int counter = 0;

    public void addStation(RadioStation station) {
        stations.add(station);
    }

    public void removeStation(RadioStation toRemove) {
        float toRemoveFrequency = toRemove.getFrequency();
        stations.removeIf(station -> station.getFrequency() == toRemoveFrequency);
    }

    public int count() {
        return stations.size();
    }

    @Override
    public Iterator<RadioStation> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return counter < stations.size();
            }

            @Override
            public RadioStation next() {
                return stations.get(counter++);
            }
        };
    }
}
