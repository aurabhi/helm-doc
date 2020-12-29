# helm-doc
A maven plugin to generate docs for helm charts. Supports generating docs of subcharts optionally.

As there are no comment preserving yaml parsers, the plugin just goes through all the yaml comments (**#**) and builds the docs from them. 

The docs are generated in **html** format. The generated html files use [Bulma](https://bulma.io/) css.

### Sample POM 
```xml
<build>
  <plugins>
      ...
    <plugin>
        <groupId>io.github.aurabhi</groupId>
        <artifactId>helm-doc-maven-plugin</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <configuration>
            <helmSourceDir>./src/helm/</helmSourceDir>
            <destinationDir>./target/helm-docs/</destinationDir>
            <generateDependencyDocs>true</generateDependencyDocs>
        </configuration>
        <goals>
        <goal>generate</goal>
        </goals>
    </plugin>
  </plugins>
</build>
```

### Usage
**mvn io.github.aurabhi:helm-doc-maven-plugin:0.0.1-SNAPSHOT:generate**

### Screenshot: 
<img src="https://github.com/aurabhi/helm-doc/blob/main/Screenshot.png" width="600" height="400" alt="Screenshot">


### TODO
* The existing yaml parsers do not preserve the comments. Build a comment preserving parser!
* Add *mvn plugin docs*
* Support **Helm** **V2** and **V3** charts.
* Add all the fields of the chart to the generated files.
* Handle **tar.gz**'ed sub charts. 
* Support generating in other formats like **markdown**
* Embedd helm chart icon(**V3**) into the generated docs.
* Make a nice icon. 

