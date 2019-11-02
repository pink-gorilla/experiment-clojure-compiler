(ns pinkgorilla.compile.analyzer
  "stolen from:  https://github.com/jsa-aerial/saite/blob/master/src/cljs/aerial/saite/analyzer.clj
"
  (:require
   [cljs.env :as env]))

(defmacro analyzer-state [[_ ns-sym]]
  `'~(get-in @env/*compiler* [:cljs.analyzer/namespaces ns-sym]))




  