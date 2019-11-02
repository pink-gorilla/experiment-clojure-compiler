(ns pinkgorilla.compile.analyzer
  "stolen from:  https://github.com/jsa-aerial/saite/blob/master/src/cljs/aerial/saite/analyzer.clj
"
  (:require
   [cljs.env :as env]))

; https://clojuredocs.org/clojure.core/unquote-splicing

(defmacro analyzer-state [[_ ns-sym]]
  `'~(get-in @env/*compiler* [:cljs.analyzer/namespaces ns-sym]))



  