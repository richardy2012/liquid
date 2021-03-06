(ns dk.salza.liq.extensions.headlinenavigator
  (:require [dk.salza.liq.editor :as editor]
            [dk.salza.liq.modes.typeaheadmode :as typeaheadmode]
            [clojure.string :as str]))

(defn callback
  [item]
  (editor/beginning-of-buffer)
  (editor/find-next item)
  (editor/top-align-page))

(defn filter-headlines
  [content]
  (->> content (str/split-lines) (filter #(re-find #"^(\(defn|function|#|;#)" %))))

;(filter-headlines)

(defn run
  []
  (spit "/tmp/tmp.txt" (pr-str (filter-headlines (editor/get-content)))) 
  (typeaheadmode/run (doall (filter-headlines (editor/get-content)))
                     str ;second
                     callback))