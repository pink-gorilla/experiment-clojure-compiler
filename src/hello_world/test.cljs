(ns hello-world.test
  (:require 
  [pinkgorilla.compile.compiler :refer [eva]]))


(def c
  " (def y 34) ")


(defn print-result 
  [result]
  (let [{value :value 
         error :error} result]
  ;(println "callback received: ")
  ;(println "type of result: " (type result))
    (if (nil? error)
        (println "eval result: " value)
        (println "eval error: " error))))


(defn xxx []
  (println "Yippie! The test module did compile without errors!"))

(defn run-test []
  (println "Running Evaluation Tests..")
  
  (eva " (+ 1 2 )" print-result) ; the simplest of all expressions
  
  (eva " (help) " print-result)  ; call function in the eval namespace. Will produce an error because no namespace referred.
  
  (eva " (pinkgorilla.compile.sandbox/help) " print-result)  ; call function in the eval namespace. Will produce an error because no namespace referred.
  ;(eva c print-result)
  
  (println "Running Evaluation Tests.. done.")
  
  )