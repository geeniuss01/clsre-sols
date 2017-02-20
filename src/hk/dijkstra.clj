(ns hk.dijkstra
  (:require [clojure.set :as s]))

(defn neighbors
  "Returns n's neighbors, optionally filtered if unvisited"
  ([g n] (get g n {}))
  ([g n uv] (select-keys (neighbors g n) uv)))

(defn update-costs
  [g costs curr unvisited]
  (let [curr-cost (costs curr)]
    (reduce
      (fn [c [nbr nbr-cost]] (update-in c [nbr] (partial min (+ curr-cost nbr-cost))))
      costs
      (neighbors g curr unvisited))))

(defn dijkstra
  [g src target]
  (loop [costs (assoc (zipmap (keys g) (repeat Long/MAX_VALUE)) src 0)
         curr src
         unvisited (disj  (apply hash-set (keys g)) src)]
    (let [costs' (update-costs g costs curr unvisited)
          curr' (first (sort-by costs' unvisited))]
      (if (= target curr')
        (costs' target)
        (recur costs'
               curr'
               (disj unvisited curr'))))))


(def g {:r {:g 2 :b 3}
        :g {:r 1 }
        :b {:r 3}})

(dijkstra g :g :b)