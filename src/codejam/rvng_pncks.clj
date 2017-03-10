(ns codejam.rvng-pncks)
;;https://code.google.com/codejam/contest/6254486/dashboard#s=p1
(defn rdc [s]
  (reduce #(if (= (last %1) %2) %1 (conj %1 %2)) [] s))

(defn cnt [v]
  (let [l (count v)]
    (if (= (last v) \+) (dec l) l)))

(defn solve[]
  (let [T (Integer/parseInt (read-line))]
    (dotimes [t T]
      (println (str "Case #" (inc t) ": " (cnt (rdc (read-line))))))))
