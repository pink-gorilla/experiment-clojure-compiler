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
       (cljs.js/eval-str state source nil
                         {:eval cljs.js/js-eval
                          :context :expr
                          :ns nssym
                          ;:load loader-fn
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
  (cljs.js/load-analysis-cache! state 're-com.core (analyzer-state 're-com.core))
)

;; path to Transit encoded analysis cache
(def cache-url "/assets/js/cljs/core.cljs.cache.aot.json")

(defn load-analysis-cache-from-url []
  (http/get cache-url
            (fn [json]
              (let [rdr   (transit/reader :json)
                    cache (transit/read rdr json)]
                (cljs.js/load-analysis-cache! state 'cljs.core cache)
        ;; ...
                ))))



(load-analysis-cache!)
  
   
   