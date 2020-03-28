package com.samsung.tcm.tca.standalone;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class MainTest {
    private Logger logger = LoggerFactory.getLogger(MainTest.class);

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void pNoOption() {
        Main spyMain = spy(new Main());

        AnalysisExecuter mockAnalysisExecuter = mock(AnalysisExecuter.class);
        spyMain.setAnalysisExecuter(mockAnalysisExecuter);

        int exitCode = new CommandLine(spyMain).execute(new String[]{});
        assertThat(exitCode, is(CommandLine.ExitCode.OK));

        ArgumentCaptor<RunnerFactory> argument = ArgumentCaptor.forClass(RunnerFactory.class);
        then(mockAnalysisExecuter).should().execute(argument.capture(), isNull());
        then(mockAnalysisExecuter).shouldHaveNoMoreInteractions();
        assertThat(argument.getValue(), instanceOf(RunnerFactory.class));
    }

    @Test
    public void pLocalOption() {
        Main spyMain = spy(new Main());

        AnalysisExecuter mockAnalysisExecuter = mock(AnalysisExecuter.class);
        spyMain.setAnalysisExecuter(mockAnalysisExecuter);

        int exitCode = new CommandLine(spyMain).execute(new String[]{"--local"});
        assertThat(exitCode, is(CommandLine.ExitCode.OK));

        ArgumentCaptor<RunnerFactory> argument = ArgumentCaptor.forClass(RunnerFactory.class);
        then(mockAnalysisExecuter).should().execute(argument.capture(), isNull());
        then(mockAnalysisExecuter).shouldHaveNoMoreInteractions();
        assertThat(argument.getValue(), instanceOf(RunnerFactoryWithLocal.class));
    }

    @Test
    public void pOutDirOption() {
        Main spyMain = spy(new Main());

        AnalysisExecuter mockAnalysisExecuter = mock(AnalysisExecuter.class);
        spyMain.setAnalysisExecuter(mockAnalysisExecuter);

        int exitCode = new CommandLine(spyMain).execute(new String[]{"--outDir", "/tmp"});
        assertThat(exitCode, is(CommandLine.ExitCode.OK));

        ArgumentCaptor<RunnerFactory> argument = ArgumentCaptor.forClass(RunnerFactory.class);
        then(mockAnalysisExecuter).should().execute(argument.capture(), eq("/tmp"));
        then(mockAnalysisExecuter).shouldHaveNoMoreInteractions();
        assertThat(argument.getValue(), instanceOf(RunnerFactory.class));
    }

    @Test
    public void nOutDirOptionWithoutValue() {
        Main spyMain = spy(new Main());

        AnalysisExecuter mockAnalysisExecuter = mock(AnalysisExecuter.class);
        spyMain.setAnalysisExecuter(mockAnalysisExecuter);

        int exitCode = new CommandLine(spyMain).execute(new String[]{"--outDir"});
        assertThat(exitCode, is(CommandLine.ExitCode.USAGE));
    }

    @Test
    public void copyResourceDirectory() throws IOException {
        File createdFolder = folder.newFolder("samples");
        logger.info("createdFolder = {}", createdFolder.getAbsolutePath());

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("samples");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String fileName = null;
        while ((fileName = br.readLine()) != null) {
            InputStream is = classLoader.getResourceAsStream("samples" + File.separator + fileName);
            File targetFile = new File(createdFolder.getAbsolutePath() + File.separator + fileName);
            Files.copy(is, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        br.close();

        inputStream.close();
    }
}
