(ns culture.facts
  "Country-level regional-culture catalog for Thailand (THA) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"THA"
   [{:culture/id "tha.dish.pad-thai"
     :culture/name "Pad Thai"
     :culture/name-local "ผัดไทย"
     :culture/country "THA"
     :culture/kind :dish
     :culture/summary "Stir-fried rice noodle dish commonly served as street food in Thailand, typically made with rice noodles, shrimp, peanuts, eggs and bean sprouts."
     :culture/url "https://en.wikipedia.org/wiki/Pad_thai"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tha.dish.tom-yum"
     :culture/name "Tom yum"
     :culture/name-local "ต้มยำ"
     :culture/country "THA"
     :culture/kind :dish
     :culture/summary "Family of hot and sour Thai soups originating from Central Thailand."
     :culture/url "https://en.wikipedia.org/wiki/Tom_yum"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tha.dish.green-curry"
     :culture/name "Green curry"
     :culture/name-local "แกงเขียวหวาน"
     :culture/country "THA"
     :culture/kind :dish
     :culture/summary "Variety of curry originating from central Thailand, made with coconut milk, green curry paste and green chilies; known locally as kaeng khiao wan."
     :culture/url "https://en.wikipedia.org/wiki/Green_curry"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tha.dish.mango-sticky-rice"
     :culture/name "Mango sticky rice"
     :culture/name-local "ข้าวเหนียวมะม่วง"
     :culture/country "THA"
     :culture/kind :dish
     :culture/summary "Traditional Southeast Asian and South Asian dessert made with glutinous rice, fresh mango and coconut milk, known in Thailand as khao niao mamuang."
     :culture/url "https://en.wikipedia.org/wiki/Mango_sticky_rice"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tha.craft.thai-silk"
     :culture/name "Thai silk"
     :culture/country "THA"
     :culture/kind :craft
     :culture/summary "Silk produced from the cocoons of Thai silkworms, with the Bombyx mori variety producing the glossy mulberry silk that became internationally recognized following World War II."
     :culture/url "https://en.wikipedia.org/wiki/Thai_silk"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tha.festival.songkran"
     :culture/name "Songkran"
     :culture/name-local "สงกรานต์"
     :culture/country "THA"
     :culture/kind :festival
     :culture/summary "Thailand's traditional New Year festival celebrated annually around 13-15 April, featuring water fights and ritual cleansing practices rooted in Buddhist and solar calendar traditions."
     :culture/url "https://en.wikipedia.org/wiki/Songkran_(Thailand)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tha.heritage.ayutthaya"
     :culture/name "Ayutthaya Historical Park"
     :culture/country "THA"
     :culture/kind :heritage
     :culture/summary "Historical park in Thailand, part of which was declared a UNESCO World Heritage Site in 1991."
     :culture/url "https://en.wikipedia.org/wiki/Ayutthaya_Historical_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tha.heritage.sukhothai"
     :culture/name "Historic Town of Sukhothai and Associated Historic Towns"
     :culture/country "THA"
     :culture/kind :heritage
     :culture/summary "UNESCO World Heritage Site inscribed in 1991, comprising three historical parks in Thailand preserving remains of the Sukhothai Kingdom that flourished during the 13th and 14th centuries."
     :culture/url "https://en.wikipedia.org/wiki/Historic_Town_of_Sukhothai_and_Associated_Historic_Towns"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-tha culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "THA"))
                 " THA entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
