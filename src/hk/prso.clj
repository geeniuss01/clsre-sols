(ns hk.prso)
;;problem-solving

(def n 5 )
(def k 1)
(def vis [5 3 4 5 6 ])

(defn solve [k vis]
  (second (reduce #(if (>= (Math/abs (- %2 (first %1))) k) [%2 (second %1)] [%2 (inc (second %1))]) [(first vis) 1] (next vis))))


(defn -m []
  (let [read-int (fn []
                   (Integer/parseInt (clojure.string/trim (read-line))))
        read-int-seq (fn []
                       (map #(Integer/parseInt %) (clojure.string/split (read-line) #"\s+")))
        T (read-int)]
    (dotimes [t T]
      (let [[n k] (read-int-seq) vis (read-int-seq)]
        (println (solve k vis))))))


;;(-m)

(solve 1 [4 0 0])