(ns jiksnu.modules.rdf.sections.conversation-sections-test
  (:require [ciste.config :refer [with-environment]]
            [ciste.core :refer [with-context with-serialization with-format]]
            [ciste.sections.default :refer [index-block index-section
                                            show-section]]
            [clj-factory.core :refer [factory]]
            [clojure.tools.logging :as log]
            [jiksnu.mock :as mock]
            jiksnu.modules.rdf.sections.conversation-sections
            [jiksnu.test-helper :refer [check context future-context
                                        test-environment-fixture select-by-model]]
            [midje.sweet :refer [=>]])
  (:import jiksnu.model.Conversation
           org.joda.time.DateTime))

(test-environment-fixture

 (context #'index-block
   (context "Conversation"
     (context "when the serialization is :http"
       (with-serialization :http

         (context "when the format is :rdf"
           (with-format :rdf

             (context "when given real conversations"
               (let [n 5
                     items (doall (for [i (range n)]
                                    (mock/a-conversation-exists)))]
                 (index-block items) =>
                 (check [response]
                        response => seq?
                        response => (partial every? vector?)
                        response => (partial every? #(= (count %) 3)))))))))))

 (context #'index-section
   (context "Conversation"
     (context "when the serialization is :http"
       (with-serialization :http

         (context "when the format is :rdf"
           (with-format :rdf

             (context "when given real conversations"
               (let [n 5
                     items (doall (for [i (range n)]
                                    (mock/a-conversation-exists)))]
                 (index-section items) =>
                 (check [response]
                        response => seq?
                        response => (partial every? vector?)
                        response => (partial every? #(= (count %) 3)))))))))))

 (context #'show-section
   (context "Conversation"
     (context "when the serialization is :http"
       (with-serialization :http

         (context "when the format is :rdf"
           (with-format :rdf

             (context "when given a real conversation"
               (let [item (mock/a-conversation-exists)]

                 (show-section item) =>
                 (check [response]
                        response => (partial every? vector?))))
             ))
         )))
   )
 )
