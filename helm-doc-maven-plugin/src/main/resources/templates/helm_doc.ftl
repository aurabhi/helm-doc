<!DOCTYPE html>
<html lang="en" >
  <head>
    <meta charset="UTF-8">
    <title>${chart.name} - Helm Docs</title>
    <link rel="icon" type="image/png" sizes="32x32" href="../images/favicon.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <!-- Bulma Version 0.9.0-->
    <link rel='stylesheet' href='https://unpkg.com/bulma@0.9.0/css/bulma.min.css'>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.9.1/css/OverlayScrollbars.min.css'>
    <link rel='stylesheet' href='https://kingsora.github.io/OverlayScrollbars/etc/os-theme-thin-dark.css'>
    <link href="https://myCDN.com/prism@v1.x/themes/prism.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/7dc3015a44.js" crossorigin="anonymous"></script>
    <style type="text/css">
      body,
      html {
        background: #f2f2f2;
      }

      pre,
      .message {
        max-width: 960px;
      }

      li {margin: 10px}

      .hero.is-primary {
      background: linear-gradient(to top right, #524ad0 10%, #D099FA);
      }

      .box {
        box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.2);
      }

      .box span.icon {
        float: right;
        font-size: 1.7em;
        padding: 2rem 2rem 0 0;
      }

      .is-large.fab {
        font-size: 7em;
      }

      .is-large.fas {
        font-size: 5em;
        margin-left: 0.2em;
      }

      .media-content {overflow: hidden;}

      .menu-list li a:hover {
        background: #d9d9d9;
      }

      .token.number {
        display: inline;
        padding: inherit;
        font-size: inherit;
        line-height: inherit;
        text-align: inherit;
        vertical-align: inherit;
        border-radius: inherit;
        font-weight: inherit;
        white-space: inherit;
        background: inherit;
        margin: inherit;
      }
      .footer {background-color: white;}

      code[class*='language-'],
      pre[class*='language-'] {
        color: rgb(0, 0, 0);
      }



      code[class*='language-yaml'],
      pre[class*='language-yaml'] {
        color: rgb(0, 0, 0);
        font-family: 'Operator Mono', 'Roboto Mono', Menlo, Consolas, Monaco, 'Andale Mono', 'Ubuntu Mono',
          monospace;
        text-align: left;
        white-space: pre;
        word-spacing: normal;
        word-break: normal;
        word-wrap: normal;
        line-height: 2;
        font-size: 1rem;
        -webkit-overflow-scrolling: touch;
        margin: 0;

        -moz-tab-size: 4;
        -o-tab-size: 4;
        tab-size: 4;

        -webkit-hyphens: none;
        -moz-hyphens: none;
        -ms-hyphens: none;
        hyphens: none;
      }

      pre[class*='language-']::-moz-selection,
      pre[class*='language-'] ::-moz-selection,
      code[class*='language-']::-moz-selection,
      code[class*='language-'] ::-moz-selection {
        text-shadow: none;
        background: #131824;
      }

      pre[class*='language-']::selection,
      pre[class*='language-'] ::selection,
      code[class*='language-']::selection,
      code[class*='language-'] ::selection {
        text-shadow: none;
        background: #131824;
      }

      @media print {
        code[class*='language-'],
        pre[class*='language-'] {
          text-shadow: none;
        }
      }


      pre[class*='language-'] {
        overflow: auto;
        padding: 0.75rem 1.25rem;
      }

      pre.is-option {
        margin: 0;
        padding: 0;
      }

      :not(pre) > code[class*='language-'],
      pre[class*='language-'] {
        background: linear-gradient(-30deg, #273149, #1c273f);
        border-radius: 0.25rem;
      }

      :not(pre) > code[class*='language-'] {
        padding: 0.1em;
        border-radius: 0.3em;
        white-space: normal;
      }

      .token.comment,
      .token.prolog,
      .token.doctype,
      .token.cdata {
        color: #8ca2d3;
      }

      .token.selector,
      .token.attr-name {
        color: #c7f683;
      }

      .token.punctuation {
        color: #5ac8e3;
      }

      .namespace {
        opacity: 0.7;
      }

      .token.tag {
        color: #2cefd8;
      }

      .token.property,
      .token.boolean,
      .token.number,
      .token.constant,
      .token.symbol,
      .token.deleted {
        color: #85b4ff;
      }

      .token.string,
      .language-css .token.string,
      .token.url,
      .token.attr-value,
      .token.char,
      .token.builtin,
      .token.inserted {
        color: #ffd694;
      }

      .token.operator,
      .token.entity,
      .style .token.string {
        color: #ff9bbe;
      }

      .token.important,
      .token.atrule,
      .token.keyword {
        color: #b7adff;
      }

      .token.function {
        color: #25d0e5;
      }

      .token.regex,
      .token.variable {
        color: #00a8d4;
      }

      .token.bold {
        font-weight: bold;
      }
      .token.italic {
        font-style: italic;
      }

      .token.entity {
        cursor: help;
      }

      .tabcontent {
          display: none;
          animation: fadeEffect 1s; /* Fading effect takes 1 second */
      }
      /* Go from zero to full opacity */
      @keyframes fadeEffect {
          from {opacity: 0;}
          to {opacity: 1;}
      }
    </style>
    <script type="text/javascript">
      function switchTab(evt, tabTitle) {
          var i, tabcontent, tablinks;
          tabcontent = document.getElementsByClassName("tabcontent");
          for (i = 0; i < tabcontent.length; i++) {
              tabcontent[i].style.display = "none";
          }
          tablinks = document.getElementsByClassName("tablinks");
          for (i = 0; i < tablinks.length; i++) {
              tablinks[i].className = tablinks[i].className.replace(" is-active", "");
          }
          document.getElementById(tabTitle).style.display = "block";
          evt.currentTarget.className += " is-active";
      }
    </script>
  </head>
  <body>
    <section class="hero is-primary">
      <div class="hero-body">
        <div class="columns">
          <div class="column is-12">
            <div class="container content">
              <div class="columns">
                <div class="column is-1-3 is-centered">
                  <h1 class="title">&nbsp&nbsp&nbsp&nbspHelm</h1>
                  <i class="is-large fas fa-ship"></i>
                  <h1 class="title">&nbsp&nbsp&nbsp&nbspDocs</h1>
                </div>
                <div class="column is-1-3">
                  <h1 class="title">${chart.name}</h1>
                  <h3 class="subtitle">
                    ${chart.version}
                  </h3>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="section">
      <div class="container">
        <div class="columns">
          <div class="column is-3">
              <aside class="is-medium menu">
                <#if isDep>
                  <i class="fas fa-code"></i> Root<br>
                  <a href="../${rootHelm}.html" class="is-active"><span class="tag is-white is-medium"> ${rootHelm}</span></a><br>
                  <br>
                  <#if rootHelm != parentHelm >
                    <i class="fas fa-code"></i> Parent<br>
                    <a href="./${parentHelm}.html" class="is-active"><span class="tag is-white is-medium"> ${parentHelm}</span></a><br>
                    <br>
                  </#if>
                </#if>

                <#if chart.dependencies??>
                  <p class="menu-label">
                      Dependencies
                  </p>
                  <#list chart.dependencies as dependency>
                    <ul class="menu-list">
                      <#if !isDep>
                          <li><a href="./dependencies/${dependency.name}.html"><span class="tag is-white is-medium">${dependency.name}</span></a></li>
                      <#else>
                          <li><a href="./${dependency.name}.html"><span class="tag is-white is-medium">${dependency.name}</span></a><br></li>
                      </#if>
                    </ul>
                  </#list>
                  <hr>
                </#if>
            </aside>
          </div>
          <div class="column is-10">
            <div class="column content is-medium">
              <h4 id="let" class="title is-3">Details</h4>
              <div class="columns box">
                <div class="column is-half">
                    <table class="table is-bordered is-striped is-fullwidth">
                      <tr>
                          <th>kubeVersion</th>
                          <td>${chart.kubeVersion}</td>
                      </tr>
                      <tr>
                          <th>appVersion</th>
                          <td>${chart.appVersion}</td>
                      </tr>
                      <tr>
                          <th>description</th>
                          <td>${chart.description}</td>
                      </tr>
                    </table>
                </div>
                <div class="column is-half is-fullwidth">
                    <table class="table is-bordered is-striped is-justify-content-end">
                        <#if chart.maintainers??>
                        <tr>
                            <th>Maintainers</th>
                            <td>
                                <#list chart.maintainers as maintainer>
                                    <ul> <b>${maintainer.name}</b> - <a href="${maintainer.email}">${maintainer.email}</a> </ul>
                                </#list>
                            </td>
                        </tr>
                        </#if>
                        <#if chart.sources??>
                        <tr>
                            <th>Sources</th>
                            <td>
                                <ul>
                                    <#list chart.sources as source>
                                        <li> <a href='${source}'>${source}</a> </li>
                                    </#list>
                                </ul>
                            </td>
                        </tr>
                        </#if>
                        <#if chart.keywords??>
                        <tr>
                            <th>Keywords</th>
                            <td>
                                <#list chart.keywords as keyword>
                                    <span class="tag is-primary is-normal is-light">${keyword}</span>
                                </#list>
                            </td>
                        </tr>
                        </#if>
                    </table>
                </div>
            </div>

            <div class="tabs is-left is-medium">
              <ul>
                <li id="" class="tablinks is-active">
                  <a onClick="switchTab(event, 'Parameters')">
                  <span class="icon is-small"><i class="fa fa-table"></i></span>
                  <span>Parameters</span>
                </a>
                </li>
                <li class="tablinks">
                  <a onClick="switchTab(event, 'Source')">
                  <span class="icon is-small"><i class="fa fa-code"></i></span>
                  <span>Source</span>
                </a>
                </li>
              </ul>
            </div>
            <div id="Parameters" class="columns box content tabcontent">
              <div class="column content table-container">
                <table class="table is-bordered is-striped is-fullwidth">
                  <colgroup>
                     <col span="1" style="width: 20%;">
                     <col span="1" style="width: 20%;">
                     <col span="1" style="width: 60%;">
                  </colgroup>
                  <thead>
                      <tr class="th is-selected">
                          <th>Param</th>
                          <th>Default Value</th>
                          <th>Description</th>
                      </tr>
                  </thead>
                  <#list params as param>
                          <tr>
                              <td>${param.name}</td>
                              <td>${param.value}</td>
                              <td>${param.docs}</td>
                          </tr>
                  </#list>
                </table>
              </div>
          </div>
          <div id="Source" class="columns box content tabcontent">
            <pre class="">
                <code class="language-yaml">
<#if srcList??>
  <#list srcList as line>
  ${line}
  </#list>
</#if>
                </code>
              </pre>
          </div>
        </div>
      </div>
</section>
<footer class="footer">

</footer>
<script src='https://cdnjs.cloudflare.com/ajax/libs/prism/9000.0.1/prism.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.9.1/js/OverlayScrollbars.min.js'></script>

<script>
  window.addEventListener('resize', () => {
  const divs = document.querySelectorAll(".menu-list");
  if (window.innerWidth < 768){
    divs.forEach(div => div.classList.add("tags"));
  }
  else {
    divs.forEach(div => div.classList.remove("tags"));
  }
});

  document.addEventListener("DOMContentLoaded", function() {
  //The first argument are the elements to which the plugin shall be initialized
  //The second argument has to be at least a empty object or a object with your desired options
  OverlayScrollbars(document.querySelectorAll("body"), { });
});

</script>
</body>
</html>