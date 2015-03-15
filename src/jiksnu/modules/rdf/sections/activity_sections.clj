(ns jiksnu.modules.rdf.sections.activity-sections
  (:require [ciste.sections :refer [defsection]]
            [ciste.sections.default :refer [show-section index-block index-line]]
            [clojure.tools.logging :as log]
            [jiksnu.model.activity :as model.activity]
            [jiksnu.model.user :as model.user]
            [jiksnu.namespace :as ns]
            [jiksnu.modules.rdf.util :as rdf]
            [plaza.rdf.core :as plaza]
            [slingshot.slingshot :refer [throw+]])
  (:import jiksnu.model.Activity))

(defsection index-block [Activity :rdf]
  [items & [response & _]]
  (apply concat (map #(index-line % response) items)))

(defsection show-section [Activity :rdf]
  [activity & _]
  (plaza/with-rdf-ns ""
    (let [{:keys [id published content]} activity
          uri (:id activity)
          user (model.activity/get-author activity)
          user-res (plaza/rdf-resource (or #_(:id user) (model.user/get-uri user)))]
      (concat
       (rdf/with-subject uri
         (concat
          [
           [[ns/rdf  :type]        [ns/sioc "Post"]]
           [[ns/as   :verb]        (plaza/l "post")]
           [[ns/sioc :has_creator] user-res]
           [[ns/sioc :has_owner]   user-res]
           [[ns/as   :author]      user-res]
           ]
          (when-let [lit (some-> published .toDate plaza/date)]
            [
             [[ns/dc   :published]   lit]
             ])))
       (when content [[uri [ns/sioc  :content]    (plaza/l content)]])))))

