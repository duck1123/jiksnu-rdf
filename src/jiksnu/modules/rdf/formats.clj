(ns jiksnu.modules.rdf.formats
  (:require [ciste.formats :refer [format-as]]
            [clojure.tools.logging :as log]
            [jiksnu.modules.rdf.util :as rdf]))

(defmethod format-as :n3
  [request format response]
  (-> response
      (assoc :body (rdf/format-triples (:body response) :n3))
      (assoc-in [:headers "Content-Type"] "text/plain; charset=utf-8")))

(defmethod format-as :rdf
  [request format response]
  (-> response
      (assoc :body (rdf/format-triples (:body response) :xml-abbrev))
      (assoc-in [:headers "Content-Type"] "application/rdf+xml; charset=utf-8")))


