(ns jiksnu.modules.rdf.views.stream-views-test
  (:require [ciste.core :refer [with-context with-serialization with-format
                                *serialization* *format*]]
            [ciste.formats :refer [format-as]]
            [ciste.filters :refer [filter-action]]
            [ciste.views :refer [apply-view]]
            [clj-factory.core :refer [factory]]
            [clojure.tools.logging :as log]
            [jiksnu.actions.stream-actions :refer [public-timeline user-timeline]]
            [jiksnu.actions.user-actions :as actions.user]
            [jiksnu.db :as db]
            [jiksnu.mock :as mock]
            [jiksnu.modules.rdf.util :as rdf]
            jiksnu.modules.rdf.views.stream-views
            [jiksnu.test-helper :refer [check context future-context
                                        test-environment-fixture]]
            [midje.sweet :refer [=> contains truthy]]))

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
                 (contains
                  {:body (partial every? vector?)})))))))))

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
