(defproject net.kronkltd/jiksnu-rdf "0.1.0-SNAPSHOT"
  :description "RDF Module for Jiksnu"
  :url "https://github.com/duck1123/jiksnu-rdf"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[ciste "0.6.0-SNAPSHOT"]
                 [clj-factory "0.2.2-SNAPSHOT"]
                 [clj-stacktrace "0.2.8"]
                 [clojurewerkz/support "0.20.0"]
                 [net.kronkltd/jiksnu-core "0.1.0-SNAPSHOT"]
                 [net.kronkltd/plaza "0.3.0-SNAPSHOT"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-api "1.7.12"]
                 [org.slf4j/slf4j-log4j12 "1.7.12"]
                 [slingshot "0.12.2"]]

  :profiles {:dev
             {:resource-paths ["test-resources"]
              :dependencies
              [[midje         "1.7.0"]
               [ring-mock     "0.1.5"]]}}


  :plugins [[codox          "0.6.1"]
            [lein-midje     "3.0-beta1"]]

  )
