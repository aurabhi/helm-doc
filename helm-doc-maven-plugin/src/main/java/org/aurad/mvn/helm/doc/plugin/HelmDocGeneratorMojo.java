package org.aurad.mvn.helm.doc.plugin;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.aurad.mvn.helm.doc.plugin.model.Chart;
import org.aurad.mvn.helm.doc.plugin.model.Dependency;
import org.aurad.mvn.helm.doc.plugin.model.HelmDoc;
import org.aurad.mvn.helm.doc.plugin.model.ValuesParameter;

import java.io.IOException;
import java.util.List;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class HelmDocGeneratorMojo  extends AbstractMojo {

    private static final String VALUES_FILE = "values.yaml";

    private static final String CHART_FILE = "Chart.yaml";

    //TODO: handle Helm 2 requirements.yaml file.
    //private static final String REQUIREMENTS_FILE = "requirements.yaml";

    private static final String DEP_CHART_DIR = "charts/";

    @Parameter(property ="helmSourceDirectory", defaultValue="./src/helm/")
    private String helmSourceDir;

    @Parameter(property ="docsDestinationDirectory", defaultValue = "./target/helm-docs/")
    private String destinationDir;

    @Parameter(property = "valuesFileName", defaultValue = "values.yaml")
    private String fileName;

    @Parameter(property = "generateDependencyDocs", defaultValue = "false")
    private boolean generateDependencyDocs;

    @Parameter(defaultValue = "html")
    private String format;

    @Parameter(property = "phase")
    private String phase;

    private String rootHelm = null;

    private DocGenerator docGenerator;

    private Log log = getLog();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {


        log.info("Running");
        log.info("Helm Source Directory: " + helmSourceDir);
        log.info("Destination directory for generated docs: " + destinationDir);
        log.info("Generated file format: " + format);
        log.info("Generate Dependency Docs: " + generateDependencyDocs);
        log.info("Execution phase: " + phase);

        this.init();
        this.generateDocs(this.helmSourceDir, false, null);

    }

    private void init() {
        Utils.mkdirP(this.destinationDir);
        log.debug("Created the destination directory");

        try {
            this.docGenerator = new HtmlDocGenerator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateDocs(String chartDir, boolean isDepedency, String parent) {
        try {
            Chart chart = Utils.loadChart(chartDir + "/" + CHART_FILE);
            log.info(" Loaded Chart: " + chart.getName() );
            log.debug(" Is Chart: " + chart.getName() + " a dependency: " + isDepedency );
            if( !isDepedency ) {
                this.rootHelm = chart.getName();
            }

            List<ValuesParameter> valuesParameters = Utils.parseYamlToValueParameters(chartDir + "/" + VALUES_FILE);

            HelmDoc helmDoc = new HelmDoc();
            helmDoc.setChart(chart);
            helmDoc.setValuesParameters(valuesParameters);

            String docs = docGenerator.generate(helmDoc, rootHelm, isDepedency, parent);
            String docDestination = isDepedency ? this.destinationDir + "dependencies/" : this.destinationDir;
            Utils.saveDocToFile(docs, docDestination, chart.getName() + "." + format);
            log.info("Saved doc: "+docDestination + chart.getName() + "." + format);

            if( this.generateDependencyDocs && null != chart.getDependencies() ) {
                for(Dependency d : chart.getDependencies()) {
                    String depChartDir =  chartDir + DEP_CHART_DIR + d.getName() + "/";
                    generateDocs(depChartDir, true, chart.getName());
                }
            } else {
                return;
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

}
