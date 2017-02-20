(ns hk.ai.basic)

;;https://www.hackerrank.com/challenges/saveprincess

(defn displayPathtoPrincess [m grid]
"some thing"
  (println m grid))
        
(defn -main []
  (let [m (Integer/parseInt (read-line))
        grid (take m (repeatedly #(read-line)))]
    (displayPathtoPrincess m grid)))


(def g ["---" "-m-" "--p"])
;(map-indexed (fn [a b] (hash-map a a b b)) g)
(def ga (map-indexed (fn [i s] (hash-map :i i :m (.indexOf s "m") :p (.indexOf s "p"))) g))
;(reduce #(if ()) {} ga)
;(if (>= (:m (nth ga 1)) 0 ) (assoc-in {} [:m :i] 1) "na")
;(assoc-in {} [:m :p] 1)


