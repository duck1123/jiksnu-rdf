(ns jiksnu.modules.rdf.routes.stream-routes-test
  (:require [ciste.formats :refer [format-as]]
            [clj-factory.core :refer [factory fseq]]
            [clojure.tools.logging :as log]
            [clojurewerkz.support.http.statuses :as status]
            [jiksnu.actions.auth-actions :as actions.auth]
            [jiksnu.actions.user-actions :as actions.user]
            [jiksnu.db :as db]
            [jiksnu.mock :as mock]
            [jiksnu.model :as model]
            [jiksnu.model.activity :as model.activity]
            [jiksnu.model.subscription :as model.subscription]
            [jiksnu.model.user :as model.user]
            [jiksnu.test-helper :refer [check context future-context test-environment-fixture]]
            [jiksnu.routes-helper :refer [as-user response-for]]
            [midje.sweet :refer [=>]]
            [ring.mock.request :as req]))

(test-environment-fixture

 (context "public-timeline-http-route"

   (context "when there are activities"
     (let [user (mock/a-user-exists)]
       (dotimes [n 10]
         (mock/there-is-an-activity {:user user}))

       (context "when the the request is for n3"
         (-> (req/request :get "/api/statuses/public_timeline.n3")
             response-for) =>
             (check [response]
                    response => map?
                    (:status response) => status/success?
                    ;; TODO: parse and check model
                    (let [body (:body response)]
                      body => string?)))
       ))
   )

 (context "user timeline"

   (context "n3"
     (let [user (mock/a-user-exists)]
       (dotimes [n 10]
         (mock/there-is-an-activity {:user user}))

       (-> (req/request :get (format "/api/statuses/user_timeline/%s.n3" (:_id user)))
           (as-user user) response-for)) =>
           (check [response]
                  response => map?
                  (:status response) => status/success?
                  (:body response) => string?))
   )
 )
