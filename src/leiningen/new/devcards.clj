(ns leiningen.new.devcards
  (:require [leiningen.new.common :refer :all]))

(def devcards-assets
  [["src/cljs/{{sanitized}}/core.cljs" "reframe/src/cljs/core.cljs"]
   ["src/cljs/{{sanitized}}/db.cljs" "reframe/src/cljs/db.cljs"]
   ["src/cljs/{{sanitized}}/handlers.cljs" "reframe/src/cljs/handlers.cljs"]
   ["src/cljs/{{sanitized}}/subscriptions.cljs" "reframe/src/cljs/subscriptions.cljs"]
   ["resources/templates/cards.html" "core/resources/templates/error.cards"]])

(defn devcards-features [[assets options :as state]]
  (if (some #{"+devcards"} (:features options))
    ;; what does remove conflicting assets do?
    [(into (remove-conflicting-assets assets "core.cljs") devcards-assets)
     (-> options
         ; I think this will ripple and be a flag to test wheter or not to use in a template
         (assoc :devcards true)
         ; the dependincies get added to the project.clj
         (append-options :dependencies [['devcards "0.2.4"]]))]
    state))


;; need something n core includes and a card
;; need a new build like dev in project
;; need a new file for cards
