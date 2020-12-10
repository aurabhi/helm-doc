package org.aurad.mvn.helm.doc.plugin;

import org.aurad.mvn.helm.doc.plugin.model.HelmDoc;

public interface DocGenerator {

    String generate(HelmDoc helmDoc, String rootHelm, boolean isDependency, String parent) throws Exception;
}
