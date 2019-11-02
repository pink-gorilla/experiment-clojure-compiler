(ns pinkgorilla.compile.compiler
   (:require-macros
    [pinkgorilla.compile.analyzer :refer [analyzer-state]])
  (:require
   [cljs.core.async
    :as async
    :refer (<! >! put! chan)
    :refer-macros [go go-loop]]
   [clojure.string :as cljstr]
   [goog.string :as gstring]
   [goog.string.format]
   [cljs.tools.reader :refer [read-string]]
   [cljs.env :as env]
   [cljs.js :refer [empty-state eval js-eval eval-str require]]
   [pinkgorilla.compile.sandbox]
   ))


; compiler-state.
(def state (cljs.js/empty-state))


(def default-namespace-declaration
    "(ns blistering.sandbox) ")

(def default-dependencies
  {}) ; map of namespace name to source

(defonce namespace-declaration (atom default-namespace-declaration))
(defonce dependencies (atom default-dependencies))

(defn init [user-namespace-declaration user-dependencies]
  (reset! namespace-declaration user-namespace-declaration)
  (reset! dependencies user-dependencies)
  )


(defn loader
  "A namespace loader that looks in the dependencies bundle for required namespaces."
  [{:keys [name]} callback]
  (let [str-name (.-str name)
        source (@dependencies str-name)]
    (if source
      (js/console.log (str "PinkCompiler /Loading " str-name "."))
      (js/console.log (str "PinkCompiler / Unable to load " str-name ".")))
    (callback {:lang :clj :source (str source)})))




(def print-chan (async/chan 10))

(go-loop [msg (async/<! print-chan)]
  (js/console.log (print-str msg))
  (recur (async/<! print-chan)))

(defn printchan [& args]
  (async/put! print-chan (clojure.string/join " " args)))


(defn loader-fn [info-map cb]
  (printchan info-map)
  (cb  {:lang :js :source ""}))

(defn eva
  ([nssym source cb]
   (if (string? source)
       (cljs.js/eval-str 
        state 
        (str @namespace-declaration source)
        nil
        {:eval cljs.js/js-eval
         :context :expr
         ;:ns nssym
         ;:load loader
         :load loader-fn
         }
        cb)
     (try
       (cljs.js/eval state source
                     {:eval cljs.js/js-eval
                      :ns nssym
                      :context :expr}
                     cb)
       (catch :default cause
         (cb {:error (prn-str cause)})))))
  ([source cb]
   (eva 'pinkgorilla.compile.sandbox source cb)))


(defn load-analysis-cache! []
  (cljs.js/load-analysis-cache! state 'pinkgorilla.compile.sandbox (analyzer-state 'pinkgorilla.compile.sandbox))
  (cljs.js/load-analysis-cache! state 'fortune.core (analyzer-state 'fortune.core))
  (cljs.js/load-analysis-cache! state 're-com.core (analyzer-state 're-com.core))
  )


(load-analysis-cache!)
  
   
   