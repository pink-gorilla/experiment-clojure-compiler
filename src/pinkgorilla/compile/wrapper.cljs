(ns pinkgorilla.compile.wrapper
  (:require
    [clojure.string :as cljstr]
    [goog.string]
  ))

(def base-requires
  "[clojure.string :as str]
   [aerial.hanami.core :as hmi :refer [md]]
   [aerial.hanami.common :as hc :refer [RMV]]
   [aerial.hanami.templates :as ht]
   [aerial.saite.compiler :refer [format]]
   [aerial.saite.core :as sc :refer [read-data]]
   [com.rpl.specter :as sp]
   [re-com.core
    :refer [h-box v-box box gap line h-split v-split scroller
            button row-button md-icon-button md-circle-icon-button info-button
            input-text input-password input-textarea
            label title p
            single-dropdown
            checkbox radio-button slider progress-bar throbber
            horizontal-bar-tabs vertical-bar-tabs
            modal-panel popover-content-wrapper popover-anchor-wrapper]]
  ")

(defn add-requires [base requires]
  (reduce (fn[R r]
            (goog.string/format (str R "\n         %s") r))
          base-requires requires))

;(defn set-namespace
;  [nssym & {:keys [base requires cljrequires]
;            :or {base base-requires cljrequires []}}]
;  (evaluate
;   (format "(ns %s\n  (:require\n   %s))"
;    (name nssym)
;    (add-requires base requires))
;   println)