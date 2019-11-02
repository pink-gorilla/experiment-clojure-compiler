(ns pinkgorilla.compile.load-url)

;; https://gist.github.com/mhuebert/18a1ef480d1bb0b0a270


;; path to Transit encoded analysis cache
#_(def cache-url "/assets/js/cljs/core.cljs.cache.aot.json")

#_(defn load-analysis-cache-from-url [state]
  (http/get cache-url
            (fn [json]
              (let [rdr   (transit/reader :json)
                    cache (transit/read rdr json)]
                (cljs.js/load-analysis-cache! state 'cljs.core cache)
        ;; ...
                ))))
