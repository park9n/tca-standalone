package com.samsung.tcm.tca.standalone;

import com.ginsberg.junit.exit.ExpectSystemExit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.samsung.tcm.tca.standalone.Main.main;

public class MainTest {
    private Logger logger = LoggerFactory.getLogger(MainTest.class);

    // https://github.com/park9n/FAQ/blob/master/mockito.md#how-do-i-test-a-method-use-systemexit
    @Test
    @ExpectSystemExit
    public void pNoArgument() {
        main(new String[]{});
    }

    @Test
    @ExpectSystemExit
    public void pHelpArgument() {
        main(new String[]{"--help"});
    }

    @Test
    @ExpectSystemExit
    public void pVersionArgument() {
        main(new String[]{"--version"});
    }

    @Test
    @ExpectSystemExit
    public void pLocalArgument() {
        main(new String[]{"--version"});
    }
}
