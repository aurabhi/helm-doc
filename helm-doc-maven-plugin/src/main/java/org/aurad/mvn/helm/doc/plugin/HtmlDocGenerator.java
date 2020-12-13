package org.aurad.mvn.helm.doc.plugin;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.aurad.mvn.helm.doc.plugin.model.HelmDoc;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HtmlDocGenerator implements DocGenerator{

    private static final String TEMPLATES_RES_DIR = "/templates/";

    private Template template;

    public HtmlDocGenerator() throws IOException {

        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(this.getClass(), TEMPLATES_RES_DIR);
        cfg.setDefaultEncoding("UTF-8");

        this.template = cfg.getTemplate("helm_doc.ftl");
    }

    public String generate(HelmDoc helmDoc, String rootHelm, boolean isDependency, String parent) throws Exception {

        String docs = "";
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("rootHelm", rootHelm);
        templateData.put("parentHelm", parent);
        templateData.put("chart", helmDoc.getChart());
        templateData.put("params", helmDoc.getValuesParameters());
        templateData.put("srcList", helmDoc.getSource());
        templateData.put("isDep", isDependency);

        try (StringWriter out = new StringWriter()) {
            template.process(templateData, out);
            docs = out.getBuffer().toString();
        }
        return docs;
    }
}
