(ns marketentry.facts "Thailand market-entry catalog.")
(def catalog
  {"THA" {:name "Thailand"
          :owner-authority "CGD / e-GP (e-Government Procurement)"
          :legal-basis "Public Procurement and Supplies Administration Act"
          :national-spec "e-GP supplier registration + DBD company number"
          :provenance "https://www.gprocurement.go.th/"
          :required-evidence ["DBD company registration record" "e-GP registration record" "DBD extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / CGD"
          :rep-legal-basis "Thai legal entity registration typically required for e-GP participation"
          :rep-provenance "https://www.gprocurement.go.th/"
          :corporate-number-owner-authority "DBD / Revenue Department"
          :corporate-number-legal-basis "Company registration number / tax ID"
          :corporate-number-provenance "https://www.dbd.go.th/"}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
