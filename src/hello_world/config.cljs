(ns hello-world.config
  "a sample configuration how to use pinkgorilla.compiler

   The Compiler configuration:
   - defines which functions are available to the self hosted clojurescript. 
   - must be run at compile time of the bundle.js
   - The Namespace declaration is irrelevant in regards to making functions
     available; it is only a convenience wrapper so that snippets can be of
     shorter code-size.
"
  (:require-macros
   [pinkgorilla.compile.macros :as macro]
   [pinkgorilla.compile.analyzer :as analyzer]
   )  ; this is only at compile time.
  (:require 
     ; put here the dependencies that should go to the cljs bundle.
     ; It might be sufficient that requires in the notebook are enough. But since
     ; We do not want to have any surprises at runtime, better add them here.
   [re-com.core]
   [fortune.core]
   [hello-world.app]
   
   ))


(def namespace-declaration
  "Namespace Declaration is like a 'header' of a snippet
   The namespace that generates should depend on the notebook, so this is a %s
   namespace-declaration will get evaluated at compile time of the bundle.js
  "
  (macro/literally
   (ns blistering.sun
     (:require
      [fortune.core :refer [cookie]]))))


(def namespace-declaration2
  "Namespace Declaration is like a 'header' of a snippet
   The namespace that generates should depend on the notebook, so this is a %s
   namespace-declaration will get evaluated at compile time of the bundle.js
  "
  ; " (require '[fortune.core :refer [cookie]]) " 
  " (require '[hello-world.app :refer [help]]) "
  )



(def dependencies
  "A bundle of dependencies.
   All functions that should be available in the self hosted clojurescript have
   to be added here.
  "
  (macro/sources
  ;  (analyzer/sources
     fortune.core
     ))

; "fortune.core"


; %s

; this should work, but does not.
;  [hello-world.app :refer [help]]