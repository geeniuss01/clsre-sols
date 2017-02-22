(ns hk.wc29)

;; contests/w29/challenges/day-of-the-programmer7
(defn is-leap-russia? [y]
  (cond
    (<= 1700 y 1918) (zero? (rem y 4))
    (<= 1919 2700) (or (zero? (rem y 400)) (and (zero? (rem y 4)) (not (zero? (rem y 100)))))))

(defn day256 [year]
  (let [y (bigint year) is-leap (is-leap-russia? y)]
    (cond
      (= y 1918) "26.09.1918"
      (is-leap-russia? y) (str "12.09." y)
      :else (str "13.09." y))))


;; contests/w29/challenges/big-sorting
(defn -sort []
  (let [n (bigint (read-line))]
    (loop [i 0 v []]
      (if (= i n) (println (clojure.string/join "\n" (sort v)))
                  (recur (inc i) (conj v (bigint (read-line))))))))

;;(-sort)

