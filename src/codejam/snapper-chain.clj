
;; http://code.google.com/codejam/contest/433101/dashboard
(ns codejam.snapper-chain
  (:require [clojure.edn :as edn])
  (:gen-class))


(defn fill1 
  "for a given snapper chain fo len n, returns min toggles needed for light to be on"
  ([] (fill1 1))
  ([n] (lazy-seq (cons n (fill1 (+ n (inc n)))))))

(defn -main 
  "arbitrater of inp & oup"
  []
  (let [T (edn/read-string(read-line))]
    (loop [t 0] (if (= t T) 0 
                       (let [a (map clojure.edn/read-string (clojure.string/split (read-line) #" ")) n (first a ) k (second a)]
                         (println (str "Case #" (inc t) ": " (if (= 0 (rem (inc k) (inc (nth (fill1 0) n)))) "ON" "OFF")))
                         (recur (inc t)))))))
  

