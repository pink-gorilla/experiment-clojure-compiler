(ns hello-world.config
   (:require-macros
     [pinkgorilla.compile.macros :as macro])
   (:require 
     ; put here the dependencies that should go to the cljs bundle.
    [fortune.core] 
    [re-com.core]
    ))

(def dependencies
  "A bundle of dependencies."
  (macro/sources
   fortune.core

   ))


; clojure.pprint
; fortune.db
;  fortune.core
;    fortune.db

(def namespace-declaration
  (macro/literally
   (ns wonderful.world
     (:require
      [fortune.core :refer [cookie]]
      [pinkgorilla.compile.sandbox :refer [help]]

      ))))