(ns hello-world.test
  (:require 
  [pinkgorilla.compiler]))


(def c
  " (def y 34) ")


(defn print-result 
  [result]
  (println "evaluated result: " (prn result)))


(defn xxx []
  (println "xxx"))

(defn run-test []
  (println "evaling c..")
  (pinkgorilla.compiler/eva c print-result)
  (println "evaling c.. done.")
  
  )