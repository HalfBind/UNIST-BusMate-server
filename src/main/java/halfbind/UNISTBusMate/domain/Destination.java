package halfbind.UNISTBusMate.domain;

import java.util.Objects;

public enum Destination {
    KTX_ULSAN_STATION, SINBOK_ROTARY, EONYANG_TERMINAL, ULSAN_TERMINAL, ULSAN_UNIVERSITY, GUYEONG_RI, SEONGNAM, SAMSAN;

    public static Destination get(String destinationName) {
        if (Objects.equals(destinationName, "KTX_ULSAN_STATION")) {
            return Destination.KTX_ULSAN_STATION;
        }
        if (Objects.equals(destinationName, "SINBOK_ROTARY")) {
            return Destination.SINBOK_ROTARY;
        }
        if (Objects.equals(destinationName, "EONYANG_TERMINAL")) {
            return Destination.EONYANG_TERMINAL;
        }
        if (Objects.equals(destinationName, "ULSAN_TERMINAL")) {
            return Destination.ULSAN_TERMINAL;
        }
        if (Objects.equals(destinationName, "ULSAN_UNIVERSITY")) {
            return Destination.ULSAN_UNIVERSITY;
        }
        if (Objects.equals(destinationName, "GUYEONG_RI")) {
            return Destination.GUYEONG_RI;
        }
        if (Objects.equals(destinationName, "SEONGNAM")) {
            return Destination.SEONGNAM;
        }
        if (Objects.equals(destinationName, "SAMSAN")) {
            return Destination.SAMSAN;
        }
        return null;
    }
}
