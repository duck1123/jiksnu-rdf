(ns jiksnu.modules.rdf.actions.user-actions)

;; FIXME: This does not work yet
(defn foaf-query
  "Extract user information from a foaf document"
  []
  (sp/defquery
    (sp/query-set-vars [:?user :?nick :?name :?bio :?img-url])
    (sp/query-set-type :select)
    (sp/query-set-pattern
     (sp/make-pattern
      [
       [:?uri    plaza/rdf:type                     :foaf/Document]
       [:?uri    :foaf:PrimaryTopic    :?user]
       (plaza/optional [:?user :foaf/nick            :?nick])
       (plaza/optional [:?user :foaf/name            :?name])
       (plaza/optional [:?user :dcterms/descriptions :?bio])
       (plaza/optional [:?user :foaf/depiction       :?img-url])]))))

(defaction discover-user-rdf
  "Discover user information from their rdf feeds"
  [user]
  ;; TODO: alternately, check user meta
  (let [uri (:foaf-uri user)
        model (plaza/document->model uri :xml)
        query (foaf-query)]
    (sp/model-query-triples model query)))

