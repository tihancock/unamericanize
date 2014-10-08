(ns unamericanize.core
  (:require [clojure.string :refer [replace]]))

(enable-console-print!)

(println "loaded!")

(defn walk-nodes
  [node f]
  (if (.hasChildNodes node)
    (doseq [n (array-seq (.-childNodes node))] (walk-nodes n f))
    (f node)))

(walk-nodes
 js/document.body
 (fn [n] (set! (.-textContent n) (replace (.-textContent n) #"a" "fantabulouso"))))
