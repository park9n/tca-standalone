package com.samsung.tcm.tca.standalone;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// https://github.com/park9n/FAQ/blob/master/mockito.md#what-is-different-between-powermock-and-powermockito
// https://github.com/park9n/FAQ/blob/master/mockito.md#what-do-i-add-into-preparefortest-for-powermock
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(AnalysisExecuter.class)
public class AnalysisExecuterTest {
    private Logger logger = LoggerFactory.getLogger(AnalysisExecuterTest.class);

    // https://github.com/park9n/FAQ/blob/master/mockito.md#what-is-different-between-mockitomock-and-mock
    @Mock
    SchemaFetchRunner mockSchemaFetchRunner;
    // https://github.com/park9n/FAQ/blob/master/mockito.md#what-is-injectmocks
    @InjectMocks
    AnalysisExecuter analysisExecuter;

    @Test
    public void testExecuteSum() throws Exception {
        MockitoAnnotations.initMocks(this);
//        SchemaFetchRunner mockSchemaFetchRunner = mock(SchemaFetchRunner.class);

        // https://github.com/park9n/FAQ/blob/master/mockito.md#how-do-i-mock-for-new-class
//        whenNew(SchemaFetchRunner.class).withNoArguments().thenReturn(mockSchemaFetchRunner);

        when(mockSchemaFetchRunner.run(1, 2)).thenReturn(5);
        // https://github.com/park9n/FAQ/blob/master/mockito.md#what-is-bddmockito
//        given(mockSchemaFetchRunner.run(1, 2)).willReturn(5);
//        doReturn(5).when(mockSchemaFetchRunner).run(eq(1), eq(2));

//        analysisExecuter = new AnalysisExecuter();
        assertThat(analysisExecuter.executeSum(), equalTo(5));

        // https://github.com/park9n/FAQ/blob/master/mockito.md#how-do-i-mock-for-new-class
//        verifyNew(SchemaFetchRunner.class).withNoArguments();

        verify(mockSchemaFetchRunner).run(1, 2);
//        then(mockSchemaFetchRunner).should().run(1, 2);
    }
}
