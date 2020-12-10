package org.aurad.mvn.helm.doc.plugin;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.aurad.mvn.helm.doc.plugin.model.Chart;
import org.aurad.mvn.helm.doc.plugin.model.ValuesParameter;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final Log LOG = new SystemStreamLog();

    private Utils() {

    }

    public static void mkdirP(String filePath) {
        File f = new File(filePath);
        if( !f.exists() ) {
            f.mkdirs();
        }
    }

    public static List<String> getFileContent(String filePath) {
        LOG.debug("Loading values file: " + filePath);
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(new File(filePath).getAbsolutePath()))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Chart loadChart(String chartFilePath) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        return  yaml.loadAs(new FileInputStream(chartFilePath), Chart.class);
    }

    public static void saveDocToFile(String docs, String filePath, String fileName) throws IOException {
        mkdirP(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+fileName))) {
            writer.write(docs);
            writer.flush();
        }
    }

    /**
     * A really stupid yaml parser!!!
     * @param filePath
     * @return
     */
    public static List<ValuesParameter> parseYamlToValueParameters(String filePath) {

        final String VALUE_SEPERATOR = ":";
        final String YAML_COMMENT = "#";

        List<ValuesParameter> params = new ArrayList<>();
        List<String> lines = getFileContent(filePath);
        String doc = "";
        for(String line : lines ) {
            if( line.trim().startsWith(YAML_COMMENT)) {
                doc += line+"<br>"; //TODO: The line break should not be html!!
            } else  {
                ValuesParameter p = new ValuesParameter();
                p.setName(line.split(VALUE_SEPERATOR)[0]);
                if (line.split(VALUE_SEPERATOR).length > 1) {
                  p.setValue(line.split(VALUE_SEPERATOR)[1]);
                }
                p.setDocs(doc.replace(YAML_COMMENT, ""));
                params.add(p);
                doc = "";
            }
        }
        return params;
    }

}
