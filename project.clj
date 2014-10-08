(defproject unamericanize "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]]

  :plugins [[lein-cljsbuild "1.0.3"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:source-paths ["src"]
              :compiler {
                :output-to "unamericanize.js"
                :optimizations :whitespace}}]})
