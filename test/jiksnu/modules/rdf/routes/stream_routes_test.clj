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
            [jiksnu.test-helper :as th]
            #_[jiksnu.routes-helper :refer [as-user response-for]]
            [midje.sweet :refer :all]
            [ring.mock.request :as req]))

(namespace-state-changes
 [(before :contents (th/setup-testing))
  (after :contents (th/stop-testing))])

(fact "public-timeline-http-route"

  (fact "when there are activities"
    (let [user (mock/a-user-exists)]
      (dotimes [n 10]
        (mock/there-is-an-activity {:user user}))

      (fact "when the the request is for n3"
        #_(-> (req/request :get "/api/statuses/public_timeline.n3")
              response-for) =>
              (contains {:status status/success?
                         :body string?})))))

(fact "user timeline"

  (fact "n3"
    (let [user (mock/a-user-exists)]
      (dotimes [n 10]
        (mock/there-is-an-activity {:user user}))

      #_(-> (req/request :get (format "/api/statuses/user_timeline/%s.n3" (:_id user)))
            (as-user user) response-for)) =>
            (contains {:status status/success?
                       :body string?}))
  )

