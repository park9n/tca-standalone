package com.samsung.tcm.tca.standalone;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Command(name = "tca-standalone", mixinStandardHelpOptions = true, versionProvider = Main.ManifestVersionProvider.class)
public class Main implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    @Option(names = {"-l", "--local"}, description = "blah blah")
    private boolean isLocal = false;

    @Option(names = {"-o", "--outDir"}, description = "blah blah")
    private String outDir = null;

    AnalysisExecuter analysisExecuter = null;
    RunnerFactory runnerFactory = null;

    void setAnalysisExecuter(AnalysisExecuter ae) {
        analysisExecuter = ae;
    }

    void setRunnerFactory(RunnerFactory rf) {
        runnerFactory = rf;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        if (analysisExecuter == null) {
            analysisExecuter = new AnalysisExecuter();
        }

        if (isLocal) {
            runnerFactory = new RunnerFactoryWithLocal();
        } else {
            runnerFactory = new RunnerFactory();
        }
        analysisExecuter.execute(runnerFactory, outDir);
    }

    /**
     * {@link IVersionProvider} implementation that returns version information from the picocli-x.x.jar file's {@code /META-INF/MANIFEST.MF} file.
     */
    static class ManifestVersionProvider implements CommandLine.IVersionProvider {
        public String[] getVersion() throws Exception {
            Enumeration<URL> resources = CommandLine.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try {
                    Manifest manifest = new Manifest(url.openStream());
                    if (isApplicableManifest(manifest)) {
                        Attributes attr = manifest.getMainAttributes();
                        return new String[]{get(attr, "Implementation-Title") + " version \"" +
                                get(attr, "Implementation-Version") + "\""};
                    }
                } catch (IOException ex) {
                    return new String[]{"Unable to read from " + url + ": " + ex};
                }
            }
            return new String[0];
        }

        private boolean isApplicableManifest(Manifest manifest) {
            Attributes attributes = manifest.getMainAttributes();
            return "tca-standalone".equals(get(attributes, "Implementation-Title"));
        }

        private static Object get(Attributes attributes, String key) {
            return attributes.get(new Attributes.Name(key));
        }
    }
}
