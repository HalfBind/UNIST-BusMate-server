package halfbind.UNISTBusMate.domain;

import java.util.Objects;

public enum Destination {
    KTX_ULSAN_STATION, SINBOK_ROTARY, EONYANG_TERMINAL, ULSAN_TERMINAL, ULSAN_UNIVERSITY, GUYEONG_RI, SEONGNAM, SAMSAN;

    public static Destination get(String destinationName) {
        if (Objects.equals(destinationName, "KTX_ULSAN_STATION")) {
            return Destination.KTX_ULSAN_STATION;
        }
        else if (Objects.equals(destinationName, "SINBOK_ROTARY")) {
            return Destination.SINBOK_ROTARY;
        }
        else if (Objects.equals(destinationName, "EONYANG_TERMINAL")) {
            return Destination.EONYANG_TERMINAL;
        }
        else if (Objects.equals(destinationName, "ULSAN_TERMINAL")) {
            return Destination.ULSAN_TERMINAL;
        }
        else if (Objects.equals(destinationName, "ULSAN_UNIVERSITY")) {
            return Destination.ULSAN_UNIVERSITY;
        }
        else if (Objects.equals(destinationName, "GUYEONG_RI")) {
            return Destination.GUYEONG_RI;
        }
        else if (Objects.equals(destinationName, "SEONGNAM")) {
            return Destination.SEONGNAM;
        }
        else if (Objects.equals(destinationName, "SAMSAN")) {
            return Destination.SAMSAN;
        }
        return null;
    }
}
