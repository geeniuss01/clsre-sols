(ns other.small
  #_(:require [clojure.spec :as s]))


;; TODO : use spec for desc args and return

#_(s/def ::list-nonempty (s/and  vector? #(not (empty? %))) )

#_(s/valid? ::list-nonempty [])
#_(s/conform ::list-nonempty [1])


(defn get-nums-with-sum [l N]
  "find if a seq has 2 numbers whose sum is N"
  (empty? (reduce #(if (contains? %1 (- N %2)) (reduced #{}) (conj %1 %2)) #{} l)))

;;(get-nums-with-sum [1 2 5 4] 3)


(defn pp [f g]
  (f g))






