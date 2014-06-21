(defproject net.kronkltd/jiksnu-rdf "0.1.0-SNAPSHOT"
  :description "RDF Module for Jiksnu"
  :url "https://github.com/duck1123/jiksnu-rdf"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [

                 [aleph "0.3.2"]
                 [ciste "0.5.0-SNAPSHOT"]
                 [ciste/ciste-incubator "0.1.0-SNAPSHOT"
                  :exclusions [ciste/ciste-core]
                  ]
                 [clj-factory "0.2.2-SNAPSHOT"]
                 [clj-stacktrace "0.2.7"]
                 [clj-http "0.9.1"]
                 [clojurewerkz/support "0.20.0"]
                 ;; [crypto-random "1.2.0"]
                 [lamina "0.5.2"]
                 [lib-noir "0.8.1"]
                 [net.kronkltd/jiksnu-core "0.1.0-SNAPSHOT"]
                 [net.kronkltd/plaza "0.3.0-SNAPSHOT"]
                 [org.bovinegenius/exploding-fish "0.3.4"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.2.6"]
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
                 "apache-repo-snapshots" "https://repository.apache.org/content/repositories/snapshots"}


  :profiles {:dev
             {:resource-paths ["test-resources"]
              :dependencies
              [[midje         "1.6.3"]
               [ring-mock     "0.1.5"]]}}


  :plugins [[codox          "0.6.1"]
            [lein-midje     "3.0-beta1"]]

  )
