package no.lundesgaard.sudokufeud.sudokufeud_android.util;

import com.squareup.otto.Bus;

public class BusProvider {

    private static final Bus BUS = new Bus();

    private BusProvider() {};

    public static Bus getInstance() {
        return BUS;
    }
}
