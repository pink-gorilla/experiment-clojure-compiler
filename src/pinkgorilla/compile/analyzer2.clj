(ns pinkgorilla.compile.analyzer2
  "stolen from:  https://github.com/jsa-aerial/saite/blob/master/src/cljs/aerial/saite/analyzer.clj
"
  (:require
   [cljs.env :as env]))


;(defn analyzer-state-f [ns-sym]
 ; (get-in @env/*compiler* [:cljs.analyzer/namespaces ns-sym]))

(defn one [ns-sym]
  (let [xxx (str ns-sym) ; (analyzer-state-f ns-sym)
        _ (println "xxx is" (prn xxx))]
    xxx))


(defn many [names]
  (map one names)) ; str

(defmacro sources
  "Make a map of namespace name to source, looking for files on the classpath and in jars."
  [& names]
  (str names))

#_(str
   (vec
    (many names)))

;(defmacro sources
;  [& names]
;  (map one-source names))
