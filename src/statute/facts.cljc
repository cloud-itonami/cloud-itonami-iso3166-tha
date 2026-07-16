(ns statute.facts
  "General-law compliance catalog for Thailand (THA) -- extends this
  repo's existing `marketentry.facts` (narrow public-procurement
  scope) with a second, orthogonal catalog of statutes a company
  generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd/-ind/-ken's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Reuses this session's already-verified capital/organization data
  from cloud-itonami-municipality-tha-bangkok (Thailand Q869, Bangkok
  Q1861, no P36 historical-capital bug).

  Copyright Act B.E. 2537 (1994) is directly confirmed by reading
  WIPO Lex's own hosted PDF text, which explicitly states 'This Act
  may be cited as the Copyright Act, B.E. 2537' with enactment 9
  December 1994 and entry into force 21 March 1995 -- an attempt to
  use a private real-estate-company mirror (samuiforsale.com, whose
  URL happened to be indexed under faolex.fao.org's own domain in
  search results, but which rendered as a scraped commercial page
  with no enactment date shown) was explicitly rejected in favor of
  this more authoritative WIPO Lex source.

  The Personal Data Protection Act B.E. 2562 (2019) is hosted as an
  official 'Unofficial Translation' PDF on mdes.go.th (Thailand's
  Ministry of Digital Economy and Society) -- the masthead and
  Government Gazette references rendered legibly, confirming this is
  an authentic government-ministry document, but the body's exact
  promulgation-date text was garbled by font-subsetting. The 24 May
  2019 promulgation date is instead WebSearch-corroborated across
  multiple independent legal-citation sources (Norton Rose Fulbright,
  Tilleke & Gibbins, Digital Watch Observatory), all agreeing with
  each other and with the Government Gazette publication date (27 May
  2019) mentioned on the confirmed-authentic PDF itself.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"THA"
   [{:statute/id "tha.copyright-act-be-2537"
     :statute/title "Copyright Act B.E. 2537 (1994)"
     :statute/jurisdiction "THA"
     :statute/kind :law
     :statute/law-number "B.E. 2537"
     :statute/url "https://www.wipo.int/wipolex/edocs/lexdocs/laws/en/th/th001en_1.pdf"
     :statute/url-provenance :official-wipo-lex-mirror
     :statute/enacted-date "1994-12-09"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:intellectual-property}}
    {:statute/id "tha.personal-data-protection-act-be-2562"
     :statute/title "Personal Data Protection Act B.E. 2562 (2019)"
     :statute/jurisdiction "THA"
     :statute/kind :law
     :statute/law-number "B.E. 2562"
     :statute/url "https://www.mdes.go.th/law/detail/3577-Personal-Data-Protection-Act-B-E--2562--2019-"
     :statute/url-provenance :official-mdes-go-th
     :statute/enacted-date "2019-05-24"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tha statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "THA")) " THA statutes seeded with "
                 "official WIPO Lex/mdes.go.th citations. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
