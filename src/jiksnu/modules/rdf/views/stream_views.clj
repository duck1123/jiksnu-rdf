(ns jiksnu.modules.rdf.views.stream-views
  (:require [ciste.core :refer [with-format]]
            [ciste.views :refer [defview]]
            [ciste.sections.default :refer [index-section show-section]]
            [clojure.tools.logging :as log]
            [jiksnu.actions.activity-actions :as actions.activity]
            [jiksnu.actions.stream-actions :as actions.stream]
            [jiksnu.model :as model]
            [jiksnu.namespace :as ns])
  (:import jiksnu.model.Conversation))

(defview #'actions.stream/public-timeline :n3
  [request {:keys [items] :as page}]
  {:body
   (with-format :rdf
     (doall (index-section items page)))
   :template :false})

(defview #'actions.stream/public-timeline :rdf
  [request {:keys [items] :as page}]
  {:body (index-section items page)
   :template :false})

(defview #'actions.stream/user-timeline :rdf
  [request [user activities-map]]
  (when user
    {:body (->> (when-let [activities (seq (:items activities-map))]
                  (index-section activities))
                (concat (show-section user))
                doall)
     :template :false}))

(defview #'actions.stream/user-timeline :n3
  [request [user activities-map]]
  (when user
    {:body (->> (when-let [activities (seq (:items activities-map))]
                  (index-section activities))
                (concat (show-section user))
                doall
                (with-format :rdf))
     :template false}))

