(ns jiksnu.modules.rdf.views.user-views
  (:require [ciste.core :refer [with-format]]
            [ciste.views :refer [defview]]
            [ciste.sections.default :refer [show-section]]
            [clojure.tools.logging :as log]
            [jiksnu.actions.user-actions :as actions.user]
            [jiksnu.model.webfinger :as model.webfinger]
            [jiksnu.model.user :as model.user]
            [jiksnu.namespace :as ns]
            [plaza.rdf.core :as plaza]))

(defview #'actions.user/show :n3
  [request user]
  {:body
   (let [rdf-model
         (plaza/defmodel (plaza/model-add-triples
                          (with-format :rdf
                            (show-section user))))]
     (with-out-str (plaza/model->format rdf-model :n3)))
   :template :false})

(defview #'actions.user/show :rdf
  [request user]
  {:body
   (let [rdf-model (plaza/defmodel (plaza/model-add-triples (show-section user)))]
     (with-out-str (plaza/model->format rdf-model :xml-abbrev)))
   :template :false})

