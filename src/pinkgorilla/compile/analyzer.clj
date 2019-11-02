(ns pinkgorilla.compile.analyzer
  "stolen from:  https://github.com/jsa-aerial/saite/blob/master/src/cljs/aerial/saite/analyzer.clj
"
  (:require
   [cljs.env :as env]))

(defmacro analyzer-state [[_ ns-sym]]
  `'~(get-in @env/*compiler* [:cljs.analyzer/namespaces ns-sym]))


(defn one [ns-sym]
  ['ns-sym (get-in @env/*compiler* [:cljs.analyzer/namespaces ns-sym])
  ])

(defn one-hack [ns-sym]
  (let [x (name ns-sym)]
    (if (nil? x)
      "noooo"
      x))
  )

(defn collate [entries]
  (reduce conj {} entries))

(defn many [names]
  (map str names))

(defmacro sources
  "Make a map of namespace name to source, looking for files on the classpath and in jars."
  [& names]
  (vec (many names)))

;(defmacro sources
;  [& names]
;  (map one-source names))

  