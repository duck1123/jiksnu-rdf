(defproject jiksnu-rdf "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [

[aleph "0.3.2"]
                 [ciste "0.5.0-SNAPSHOT"]
                 [ciste/ciste-incubator "0.1.0-SNAPSHOT"
                  :exclusions [ciste/ciste-core]
                  ]
                 [ciste/ciste-service-aleph "0.4.0-SNAPSHOT"
                  :exclusions [ciste/ciste-core]]
                 [clj-factory "0.2.2-SNAPSHOT"]
                 [clj-stacktrace "0.2.7"]
                 [clj-statsd "0.3.10"]
                 [clj-time "0.6.0"]
                 [clj-http "0.9.1"]
                 [clojurewerkz/route-one "1.1.0"]
                 [clojurewerkz/urly "1.0.0"
                  :exclusions [com.google.guava/guava]]
                 [clojure-complete "0.2.3"]
                 [clojurewerkz/support "0.20.0"]
                 [com.novemberain/monger "1.7.0"]
                 [com.novemberain/validateur "1.7.0"]
                 [com.ocpsoft/ocpsoft-pretty-time "1.0.7"]
                 [crypto-random "1.2.0"]
                 [hiccup "1.0.5"]
                 [jayq "2.5.0"]
                 [lamina "0.5.2"]
                 [lib-noir "0.8.1"]
                 [lolg "0.1.0-SNAPSHOT"
                  :exclusions [org.clojure/google-closure-library]]
                 [net.kronkltd/jiksnu-core "0.1.0-SNAPSHOT"]
                 [net.kronkltd/plaza "0.3.0-SNAPSHOT"]
                 [org.apache.abdera/abdera-client "1.1.3"]
                 [org.bovinegenius/exploding-fish "0.3.4"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/core.cache "0.6.3"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.clojure/tools.reader "0.8.3"]
                 [org.clojure/data.json "0.2.4"]
                 [org.jsoup/jsoup "1.7.3"]
                 [org.slf4j/slf4j-api "1.7.6"]
                 [org.slf4j/slf4j-log4j12 "1.7.6"]
                 [slingshot "0.10.3"]

                 [xerces/xercesImpl "2.11.0"]

                 ;; I know you really want to upgrade this one, but
                 ;; you can't. :(
                 [xml-apis "1.4.01"]
                 #_[org.apache.httpcomponents/httpclient "4.2.5"]

                 ]

  :repositories {"stuart"                "http://stuartsierra.com/maven2"
                 "sonatype-oss-public"   "https://oss.sonatype.org/content/groups/public/"
                 "tigase-snapshots" "http://maven.tigase.org/"
                 "apache-repo-snapshots" "https://repository.apache.org/content/repositories/snapshots"}


    :profiles {:dev
             {:resource-paths ["test-resources"]
              :dependencies
              [[midje         "1.6.3"]
               [ring-mock     "0.1.5"]]}}


      :plugins [[lein-cljsbuild "0.3.2"]
            [codox          "0.6.1"]
            [lein-cucumber  "1.0.2"]
            [lein-lesscss   "1.2"]
            [lein-midje     "3.0-beta1"]]

  )
