package com.samsung.tcm.tca.standalone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

// https://github.com/park9n/FAQ/blob/master/mockito.md#what-is-different-between-powermock-and-powermockito
// https://github.com/park9n/FAQ/blob/master/mockito.md#what-do-i-add-into-preparefortest-for-powermock
@RunWith(PowerMockRunner.class)
@PrepareForTest({TCMContext.class})
public class SchemaFetchRunnerWithLocalTest {
    SchemaFetchRunnerWithLocal schemaFetchRunnerWithLocal = new SchemaFetchRunnerWithLocal();

    @Test
    public void run() throws Exception {
        // https://github.com/park9n/FAQ/blob/master/mockito.md#how-do-i-mock-singleton
        mockStatic(TCMContext.class);
        TCMContext mockContext = mock(TCMContext.class);
        mockContext.setID("myID");

        // https://github.com/park9n/FAQ/blob/master/mockito.md#how-do-i-mock-for-new-class
//        whenNew(TCMContext.class).withNoArguments().thenReturn(mockContext);

        when(TCMContext.getInstance()).thenReturn(mockContext);
        when(TCMContext.getProjectName()).thenReturn("myProject");
        when(mockContext.getID()).thenReturn("myID");
        doNothing().when(mockContext).setID(anyString());

        schemaFetchRunnerWithLocal.run(1, 2);

        // https://github.com/park9n/FAQ/blob/master/mockito.md#how-do-i-mock-for-new-class
//        verifyNew(TCMContext.class).withNoArguments();

        verifyStatic(TCMContext.class, Mockito.times(1));
        TCMContext.getInstance();

        String s = TCMContext.getProjectName();
        assertThat(s, is("myProject"));

        verifyStatic(TCMContext.class, Mockito.times(1));
        TCMContext.getProjectName();

        verify(mockContext, times(2)).setID(anyString());
        assertThat(mockContext.getID(), is("myID"));
    }
}
