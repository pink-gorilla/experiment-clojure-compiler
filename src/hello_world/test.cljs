(ns hello-world.test
  (:require 
   
   [pinkgorilla.compile.compiler :refer [init eva]]
   [hello-world.config]
   [hello-world.app]
   
   ))




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
  (println "Yippie! The test module did compile without errors!")
  (println "Namespace Definition: \n\r" hello-world.config/namespace-declaration )
  (println "Library Dependencies: \n\r" hello-world.config/dependencies)
  )

(defn run-test []
  
  (init hello-world.config/namespace-declaration  hello-world.config/dependencies)
  
  (println "Running Evaluation Tests..")
  
  (eva 'bongo.demo " (+ 1 2 )" print-result) ; the simplest of all expressions
  
  (eva 'bongo.demo " (help) " print-result)  ; call function in the eval namespace. Will produce an error because no namespace referred.
  
  (eva 'bongo.demo " (hello-world.app/help) " print-result)  ; call function in the eval namespace. Will produce an error because no namespace referred.
  
  (eva 'bongo.demo " (cookie) " print-result)

 ;(eva " (def y 34) " print-result)
  
  (println "Running Evaluation Tests.. done.")
  )

