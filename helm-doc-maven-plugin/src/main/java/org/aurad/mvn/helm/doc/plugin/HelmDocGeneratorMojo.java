package org.aurad.mvn.helm.doc.plugin;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
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

import java.io.*;
import java.util.*;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)

public class HelmDocGeneratorMojo  extends AbstractMojo {

    private static final String VALUES_FILE = "values.yaml";

    private static final String CHART_FILE = "Chart.yaml";

    //TODO: handle Helm 2 requirements.yaml file.
    //private static final String REQUIREMENTS_FILE = "requirements.yaml";

    private static final String DEP_CHART_DIR = "charts/";

    private static final String TEMPLATES_RES_DIR = "/templates/";

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

    private String baseHelm = null;

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
        File targetDir = new File(this.destinationDir);
        targetDir.mkdirs();
        log.debug("Created the destination directory");
    }

    private void generateDocs(String chartDir, boolean isDepedency, String parent) {
        try {
            Chart chart = Utils.loadChart(chartDir + "/" + CHART_FILE);
            log.info(" Loaded Chart: " + chart.getName() );
            log.debug(" Is Chart: " + chart.getName() + " a dependency: " + isDepedency );
            if( !isDepedency ) {
                this.baseHelm = chart.getName();
            }

            List<ValuesParameter> valuesParameters = Utils.parseYamlToValueParameters(chartDir + "/" + VALUES_FILE);

            HelmDoc helmDoc = new HelmDoc();
            helmDoc.setChart(chart);
            helmDoc.setValuesParameters(valuesParameters);

            String docs = generateDocInHtml(helmDoc, isDepedency, parent);
            String docDestination = isDepedency ? this.destinationDir + "dependencies/" : this.destinationDir;
            saveDocToFile(docs, docDestination, chart.getName() + "." + format);

            if( this.generateDependencyDocs && null != chart.getDependencies() ) {
                for(Dependency d : chart.getDependencies()) {
                    String depChartDir =  chartDir + DEP_CHART_DIR + d.getName() + "/";
                    generateDocs(depChartDir, true, chart.getName());
                }
            } else {
                return;
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }  catch (TemplateException e) {
            log.error("Failed running the template!!!");
            e.printStackTrace();
        }
    }

    private String generateDocInHtml(HelmDoc helmDoc, boolean isDependency, String parent) throws IOException, TemplateException {
        String docs = "";
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(this.getClass(), TEMPLATES_RES_DIR);
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("helm_doc.ftl");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("rootHelm", this.baseHelm);
        templateData.put("parentHelm", parent);
        templateData.put("chart", helmDoc.getChart());
        templateData.put("params", helmDoc.getValuesParameters());
        templateData.put("isDep", isDependency);

        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            docs = out.getBuffer().toString();
        }
        return docs;
    }


    private void saveDocToFile(String docs, String filePath, String fileName) throws IOException {
        File f = new File(filePath);
        if( !f.exists() ) {
            f.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+fileName))) {
            writer.write(docs);
            writer.flush();
            log.info("Saved doc: "+filePath+fileName);
        }
    }

}
