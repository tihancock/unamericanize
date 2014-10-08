(ns unamericanize.core
  (:require [clojure.string :refer [replace upper-case capitalize lower-case split join]]))

(def conversions
  {"zucchini"  "courgette"
   "eggplant"  "aubergine"
   "scallion"  "spring onion"
   "cilantro"  "coriander"
   "beet"      "beetroot"
   "fava bean" "broad bean"
   "garbanzo"  "chickpea"})

(defn walk-nodes
  [node f]
  (if (.hasChildNodes node)
    (doseq [n (array-seq (.-childNodes node))] (walk-nodes n f))
    (f node)))

(defn capitalization
  [term]
  (cond
   (= (upper-case term) term) upper-case
   (= (capitalize term) term) capitalize
   :else                      lower-case))

(defn replace-match
  [match to]
  (let [tokenized-to (split to #" ")]
    (join " " (map (capitalization match) tokenized-to))))

(defn unamericanize
  [input-text conversion-map]
  (reduce
   (fn [val [from to]] (.replace val (js/RegExp. from "ig") (fn [match] (replace-match match to))))
   input-text
   conversion-map))

(walk-nodes
 js/document.body
 (fn [n] (set! (.-textContent n) (unamericanize (.-textContent n) conversions))))
