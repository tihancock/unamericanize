(ns unamericanize.core)

(enable-console-print!)

(def all-nodes (-> js/document.body
                   (.getElementsByTagName "div")
                   (array-seq)))

(doseq [n all-nodes] (set! (.-innerText n) (str "TAMPERED: " (.-innerText n))))
