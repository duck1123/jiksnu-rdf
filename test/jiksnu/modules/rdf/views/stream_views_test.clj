(ns jiksnu.modules.rdf.views.stream-views-test
  (:use [ciste.core :only [with-context with-serialization with-format
                           *serialization* *format*]]
        [ciste.formats :only [format-as]]
        [ciste.filters :only [filter-action]]
        [ciste.views :only [apply-view]]
        [clj-factory.core :only [factory]]
        [jiksnu.test-helper :only [check context future-context hiccup->doc
                                   test-environment-fixture]]
        [jiksnu.actions.stream-actions :only [public-timeline user-timeline]]
        [midje.sweet :only [=> contains truthy]])
  (:require [clojure.tools.logging :as log]
            [jiksnu.actions.user-actions :as actions.user]
            [jiksnu.db :as db]
            [jiksnu.mock :as mock]
            [jiksnu.modules.rdf.util :as rdf]
            jiksnu.modules.rdf.views.stream-views))

(test-environment-fixture

 (context "apply-view #'public-timeline"
   (let [action #'public-timeline]
     (context "when the serialization is :http"
       (with-serialization :http

         (context "when the format is :n3"
           (with-format :n3

             (context "when there are conversations"
               (db/drop-all!)
               (let [n 1
                     items (doall (for [i (range n)] (mock/a-conversation-exists)))
                     request {:action action}
                     response (filter-action action request)]
                 (apply-view request response) =>
                 (check [response]
                   response => map?
                   (let [body (:body response)]
                     body => (partial every? vector?)))))
             ))
         ))))

 (context "apply-view #'user-timeline"
   (let [action #'user-timeline]
     (context "when the serialization is :http"
       (with-serialization :http

         (context "when the format is :n3"
           (with-format :n3
             (context "when that user has activities"
               (db/drop-all!)
               (let [user (mock/a-user-exists)
                     activity (mock/there-is-an-activity {:user user})
                     request {:action action
                              :params {:id (str (:_id user))}}
                     response (filter-action action request)]
                 (apply-view request response) =>
                 (check [response]
                   response => map?
                   (let [body (:body response)]
                     body => (partial every? vector?)
                     (let [m (rdf/triples->model body)]
                       m => truthy)))))))))))

 )
